using cw3.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace cw3.Services
{
    public class MockDbService
    {
        private static IEnumerable<Student> _students = new List<Student>
        {
            new Student{IdStudent=1,FirstName="Jan",LastName="Man",IndexNumber="s1000" },
            new Student{IdStudent=2,FirstName="Dzban",LastName="Cham",IndexNumber="s1001" },
            new Student{IdStudent=3,FirstName="Kan",LastName="Ram",IndexNumber="s1002" },
            new Student{IdStudent=4,FirstName="Lan",LastName="Pam",IndexNumber="s1003" }
        };
    }
}
