using cw3.DTOs.Requests;
using cw3.Models;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace cw3.Services
{
    public class MockDbService : IDbService
    {
        private static IEnumerable<Student> _students;
        static MockDbService()
        {
            _students = new List<Student>
            {
                new Student{FirstName="Jan",LastName="Man",IndexNumber="s1000" },
                new Student{FirstName="Dzban",LastName="Cham",IndexNumber="s1001" },
                new Student{FirstName="Kan",LastName="Ram",IndexNumber="s1002" },
                new Student{FirstName="Lan",LastName="Pam",IndexNumber="s1003" }
            };
        }

        public IActionResult EnrollStudent(EnrollStudentRequest request)
        {
            throw new NotImplementedException();
        }

        public IEnumerable<Student> GetStudents()
        {
            return _students;
        }

        public IActionResult PromoteStudents(int semester, string studies)
        {
            throw new NotImplementedException();
        }
    }
}
