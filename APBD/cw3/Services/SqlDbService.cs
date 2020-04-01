using cw3.Controllers;
using cw3.DTOs.Requests;
using cw3.Models;
//using Microsoft.AspNetCore.Mvc;
//using System.Net;
//using System;
//using System.Collections.Generic;
//using System.Data.SqlClient;
//using System.Linq;
//using System.Threading.Tasks;

using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Net;

namespace cw3.Services
{
    public class SqlDbService : Controller, IDbService
    {
        public IActionResult EnrollStudent(EnrollStudentRequest request)
        {
            var st = new Student()
            {
                IndexNumber = request.IndexNumber,
                FirstName = request.FirstName,
                LastName = request.LastName,
                BirthDate = request.Birthdate,
                Studies = request.Studies
            };
            //string com = "select IdEnrollment, semester, s.IdStudy, s.IdStudy from Enrollment e left join Studies s on e.IdStudy=s.IdStudy where name=@name and semester=1";
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

                        command.CommandText = "select IdEnrollment, semester, s.IdStudy from Enrollment e left join Studies s on e.IdStudy=s.IdStudy where name=@name and semester=1";
                        //command.Parameters.AddWithValue("name", request.Studies);
                        dr = command.ExecuteReader();
                        int idEnr;
                        if (!dr.Read())
                        {
                            dr.Close();
                            command.CommandText = "select MAX(IdEnrollment)+1 as max from Enrollment;";
                            dr = command.ExecuteReader();
                            if (!dr.Read()) idEnr = 0;
                            else idEnr = (int)dr["max"];
                            dr.Close();
                            command.CommandText = "INSERT INTO Enrollment VALUES(@Id, @Semester, @IdStudy, @Sdate);";
                            command.Parameters.AddWithValue("Id", idEnr);
                            command.Parameters.AddWithValue("Semester", 1);
                            command.Parameters.AddWithValue("IdStudy", idstudies);
                            command.Parameters.AddWithValue("Sdate", DateTime.Today);
                            command.ExecuteNonQuery();
                        }
                        else
                        {
                            idEnr = (int)dr["IdEnrollment"];
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
                        command.CommandText = "INSERT INTO Student VALUES(@Index, @Fname, @Lname, @Bdate, @Istudies);";
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
                            StartDate = DateTime.Today
                        };
                        return Created("nie wiem co tu wpisać ale w postmanie dziala", response);
                        return StatusCode(201, response);

                    }
                    catch (SqlException exc)
                    {
                        tran.Rollback();
                        return BadRequest("Cos poszlo nie tak");
                    }

                }

            }
        }

        public IEnumerable<Student> GetStudents()
        {
            throw new NotImplementedException();
        }

        public IActionResult PromoteStudents(int semester, string studies)
        {
            throw new NotImplementedException();
        }
    }
}
