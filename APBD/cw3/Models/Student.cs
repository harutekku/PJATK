using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Threading.Tasks;

namespace cw3.Models
{
    public class Student
    {
        public string IndexNumber { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public DateTime BirthDate { get; set; }
        public string Studies { get; set; }
        public int IdEnrollment { get; set; }

        public Student() { }
        public Student(SqlDataReader sqlDataReader)
        {
            IndexNumber = sqlDataReader["IndexNumber"].ToString();
            FirstName = sqlDataReader["FirstName"].ToString();
            LastName = sqlDataReader["LastName"].ToString();
            BirthDate = (DateTime)sqlDataReader["BirthDate"];
            IdEnrollment = (int)sqlDataReader["IdEnrollment"];
        }
    }
}
