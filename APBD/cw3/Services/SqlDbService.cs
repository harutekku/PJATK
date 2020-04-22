using cw3.Controllers;
using cw3.DTOs.Requests;
using cw3.DTOs.Responses;
using cw3.Models;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Net;
using System.Security.Claims;

namespace cw3.Services
{
    public class SqlDbService : Controller, IDbService
    {
        public IActionResult EnrollStudent(EnrollStudentRequest request)
        {
            string com = "select IdStudy, Name from Studies where name=@name";
            using (var connection = new SqlConnection("Data Source=db-mssql;Initial Catalog=kubbit;Integrated Security=True;"))
            {
                connection.Open();
                using (var tran = connection.BeginTransaction())
                using (var command = new SqlCommand(com, connection, tran))
                {
                    try
                    {
                        command.Parameters.AddWithValue("name", request.Studies);
                        var dr = command.ExecuteReader();
                        if (!dr.Read())
                        {
                            dr.Close();
                            tran.Rollback();
                            return BadRequest("Studia nie istnieja");
                        }
                        int idstudies = (int)dr["IdStudy"];
                        dr.Close();

                        command.CommandText = "select IdEnrollment, semester, s.IdStudy, StartDate from Enrollment e left join Studies s on e.IdStudy=s.IdStudy where name=@name and semester=1;";
                        dr = command.ExecuteReader();
                        int idEnr;
                        DateTime date;
                        if (!dr.Read())
                        {
                            dr.Close();
                            command.CommandText = "select MAX(IdEnrollment)+1 as max from Enrollment;";
                            dr = command.ExecuteReader();
                            if (!dr.Read()) idEnr = 0;
                            else idEnr = (int)dr["max"];
                            date = DateTime.Today;
                            dr.Close();
                            command.CommandText = "INSERT INTO Enrollment (IdEnrollment, Semester, IdStudy, StartDate) VALUES(@Id, @Semester, @IdStudy, @Sdate);";
                            command.Parameters.AddWithValue("Id", idEnr);
                            command.Parameters.AddWithValue("Semester", 1);
                            command.Parameters.AddWithValue("IdStudy", idstudies);
                            command.Parameters.AddWithValue("Sdate", date);
                            command.ExecuteNonQuery();
                        }
                        else
                        {
                            idEnr = (int)dr["IdEnrollment"];
                            date = (DateTime)dr["StartDate"];
                            dr.Close();
                        }
                        command.CommandText = "SELECT IndexNumber from Student where IndexNumber=@index;";
                        command.Parameters.AddWithValue("index", request.IndexNumber);
                        dr = command.ExecuteReader();
                        if (dr.Read())
                        {
                            dr.Close();
                            tran.Rollback();
                            return BadRequest("Student index sie powtarza");
                        }
                        dr.Close();
                        command.CommandText = "INSERT INTO Student (IndexNumber, FirstName, LastName, BirthDate, IdEnrollment) VALUES(@Index, @Fname, @Lname, @Bdate, @Istudies);";
                        command.Parameters.AddWithValue("Fname", request.FirstName);
                        command.Parameters.AddWithValue("Lname", request.LastName);
                        command.Parameters.AddWithValue("Bdate", request.Birthdate);
                        command.Parameters.AddWithValue("Istudies", idEnr);
                        command.ExecuteNonQuery();
                        tran.Commit();
                        var response = new EnrollStudentResponse()
                        {
                            LastName = request.LastName,
                            Semester = 1,
                            StartDate = date
                        };
                        return Created("nie wiem co tu wpisać ale w postmanie dziala", response);

                    }
                    catch (SqlException exc)
                    {
                        tran.Rollback();
                        return BadRequest("Cos poszlo nie tak");
                    }

                }

            }
        }
        public IActionResult PromoteStudents(PromotionRequest request)
        {
            using (var connection = new SqlConnection("Data Source=db-mssql;Initial Catalog=kubbit;Integrated Security=True;"))
            using (var command = new SqlCommand("", connection))
            {
                connection.Open();
                command.CommandText = "select * from Enrollment e left join Studies s on e.IdStudy=s.IdStudy where name=@name and semester=@semester;";
                command.Parameters.AddWithValue("name", request.Studies);
                command.Parameters.AddWithValue("semester", request.Semester);
                var dr = command.ExecuteReader();
                if (!dr.Read())
                {
                    dr.Close();
                    return NotFound("Nie ma takiego semestru na podanych studiach");
                }
                dr.Close();
                command.CommandText = "exec promote @name, @semester";
                dr=command.ExecuteReader();
                dr.Read();
                var enrollment = new PromotionResponse()
                {
                    IdEnrollment = (int)dr["IdEnrollment"],
                    Semester = (int)dr["Semester"],
                    IdStudy = (int)dr["IdEnrollment"],
                    StartDate = (DateTime)dr["StartDate"]
                };
                return Created("", enrollment);

            }
        }
        public IEnumerable<Student> GetStudents()
        {
            using (var connection = new SqlConnection("Data Source=db-mssql;Initial Catalog=kubbit;Integrated Security=True;"))
            using (var command = new SqlCommand("SELECT * FROM Student", connection))
            {
                connection.Open();
                var dr = command.ExecuteReader();
                var list = new List<Student>();
                while (dr.Read())
                {
                    var st = new Student(dr);
                    list.Add(st);
                }
                dr.Close();
                return list;
            }
        }

        public bool checkIndex(string index)
        {
            using (var connection = new SqlConnection("Data Source=db-mssql;Initial Catalog=kubbit;Integrated Security=True;"))
            using (var command = new SqlCommand("SELECT * FROM Student where IndexNumber=@index", connection))
            {
                connection.Open();
                command.Parameters.AddWithValue("index", index);
                var dr = command.ExecuteReader();
                if (!dr.Read())
                {
                    dr.Close();
                    return false;
                }
                dr.Close();
                return true;

            }
        }

        public bool checkCredentials(string index, string password)
        {
            using (var connection = new SqlConnection("Data Source=db-mssql;Initial Catalog=kubbit;Integrated Security=True;"))
            using (var command = new SqlCommand("SELECT * FROM Student where IndexNumber=@index and password=@password", connection))
            {
                connection.Open();
                command.Parameters.AddWithValue("index", index);
                command.Parameters.AddWithValue("password", password);
                var dr = command.ExecuteReader();
                bool result = dr.Read();
                dr.Close();
                return result;
            }
        }

        public IEnumerable<Claim> GetClaims(string index)
        {
            using (var connection = new SqlConnection("Data Source=db-mssql;Initial Catalog=kubbit;Integrated Security=True;"))
            using (var command = new SqlCommand("select Role from student s left join StudentRoles sr on s.IndexNumber=sr.IndexNumber left join roles r on sr.idRole=r.IdRole where IndexNumber=@index;", connection))
            {
                connection.Open();
                command.Parameters.AddWithValue("index", index);
                var dr = command.ExecuteReader();
                var list = new List<Claim>();
                list.Add(new Claim(ClaimTypes.Name, index));
                while (dr.Read())
                {
                    var claim = new Claim(ClaimTypes.Role, dr["Role"].ToString());
                    list.Add(claim);
                }
                dr.Close();
                return list;
            }
        }
    }
}
