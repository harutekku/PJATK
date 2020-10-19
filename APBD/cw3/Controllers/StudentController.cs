using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Threading.Tasks;
using cw3.Models;
using cw3.Services;
using Microsoft.AspNetCore.Mvc;

namespace cw3.Controllers
{
    [ApiController]
    [Route("api/students")]
    public class StudentController : ControllerBase
    {
        private readonly IDbService _dbService;
        public StudentController(IDbService dbService)
        {
            _dbService = dbService;
        }
        [HttpGet]

        public IActionResult getStudent()
        {

            return Ok(_dbService.GetStudents());
        }
        [HttpGet("{id}")]
        public IActionResult GetStudent(string id)
        {
            var list = new List<StudentOldClass>();
            using (var client = new SqlConnection("Data Source=db-mssql;Initial Catalog=kubbit;Integrated Security=True"))
            using (var command = new SqlCommand())
            {
                command.Connection = client;
                command.CommandText = "select indexnumber, firstname, lastname, birthdate, name, semester from student s left join enrollment e on s.idenrollment=e.idenrollment left join studies st on e.idstudy=st.idstudy where indexNumber=@id;";
                command.Parameters.AddWithValue("id", id);
                client.Open();
                var dr = command.ExecuteReader();
                while (dr.Read())
                {
                    var st = new StudentOldClass()
                    {
                        IndexNumber = dr["indexnumber"].ToString(),
                        FirstName = dr["Firstname"].ToString(),
                        LastName = dr["lastname"].ToString(),
                        BirthDate = DateTime.Parse(dr["birthdate"].ToString()),
                        Studies = dr["name"].ToString()
                    };
                    list.Add(st);
                }

            }
            return Ok(list);
        }

        [HttpPut("{id}")]
        public IActionResult PutStudent(int id)
        {
            return Ok("Sputowano " + id);
        }
        [HttpDelete("{id}")]
        public IActionResult DeleteStudent(int id)
        {
            return Ok("Usunięto " + id);
        }
        [HttpPost]
        public IActionResult CreateStudent(StudentOldClass student)
        {
            student.IndexNumber = $"s{new Random().Next(1, 20000)}";
            return Ok(student);
        }
    }
}