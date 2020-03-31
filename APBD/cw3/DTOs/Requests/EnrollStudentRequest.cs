using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace cw3.DTOs.Requests
{
    public class EnrollStudentRequest
    {
        [Required(ErrorMessage = "Musisz podać indeks")]
        public string IndexNumber { get; set; }
        [Required] 
        [MaxLength(100)]
        public string FirstName { get; set; }
        [Required]
        [MaxLength(255)]
        public string LastName { get; set; }
        [Required] 
        public DateTime Birthdate { get; set; }
        [Required]
        public string Studies { get; set; }
    }
}
