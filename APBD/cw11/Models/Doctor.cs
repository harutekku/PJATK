using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace cw11.Models
{
    public class Doctor
    {
        public int Id { get; set; }
        [Required]
        [MaxLength(100)] 
        public string FirstName { get; set; }
        [Required]
        [MaxLength(100)] 
        public string LastName { get; set; }
        [Required]
        [MaxLength(100)]
        public string Email { get; set; }
        public ICollection<Prescription> Prescriptions { get; set; }
    }
}
