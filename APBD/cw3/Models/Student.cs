using System;
using System.Collections.Generic;

namespace cw3.Models
{
    public partial class Student
    {
        public Student()
        {
            Tokens = new HashSet<Tokens>();
        }

        public string IndexNumber { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public DateTime BirthDate { get; set; }
        public int IdEnrollment { get; set; }
        public string Password { get; set; }
        public string Salt { get; set; }

        public virtual Enrollment IdEnrollmentNavigation { get; set; }
        public virtual ICollection<Tokens> Tokens { get; set; }
    }
}
