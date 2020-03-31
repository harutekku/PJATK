using cw3.DTOs.Requests;
using cw3.Models;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Threading.Tasks;

namespace cw3.Services
{
    public class SqlDbService : IDbService
    {
        public void EnrollStudent(EnrollStudentRequest request)
        {
            var st = new Student()
            {
                IndexNumber = request.IndexNumber,
                FirstName = request.FirstName,
                LastName = request.LastName,
                BirthDate =  request.Birthdate,
                Studies = request.Studies
            };
            using (var con = new SqlConnection("Data Source=db-mssql;Initial Catalog=kubbit;Integrated Security=True"))
            using (var com = new SqlCommand())
            {
                com.Connection = con;
                con.Open();
                SqlTransaction tran = con.BeginTransaction();
                try
                {
                    //1. Czy studia istnieja?
                    com.CommandText = "select IdEnrollment, semester, s.IdStudy, s.IdStudy from Enrollment e left join Studies s on e.IdStudy=s.IdStudy where name=@name and semester=1";
                    com.Parameters.AddWithValue("name", request.Studies);
                    var dr = com.ExecuteReader();
                    if (!dr.Read())
                    {
                        tran.Rollback();
                        // TODO dodawac nowe enrollmenty z dzisiejszą datą
                        //return BadRequest("Studia nie istnieja");
                    }
                    int idstudies = (int)dr["IdStudy"];
                    dr.Close();
                    com.CommandText = "INSERT INTO Student VALUES(@Index, @Fname, @Lname, @Bdate, @Istudies)";
                    com.Parameters.AddWithValue("index", request.IndexNumber);
                    com.Parameters.AddWithValue("Fname", request.FirstName);
                    com.Parameters.AddWithValue("Lname", request.LastName);
                    com.Parameters.AddWithValue("Bdate", request.Birthdate.ToString());
                    com.Parameters.AddWithValue("Istudies", idstudies);
                    com.ExecuteNonQuery();
                    tran.Commit();
                }
                catch (SqlException exc)
                {
                    tran.Rollback();
                    Console.Error.WriteLine(exc.Message);
                }
            }
        }

        public IEnumerable<Student> GetStudents()
        {
            throw new NotImplementedException();
        }

        public void PromoteStudents(int semester, string studies)
        {
            throw new NotImplementedException();
        }
    }
}
