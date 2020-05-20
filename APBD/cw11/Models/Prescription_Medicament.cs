using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace cw11.Models
{
    [Table("Prescription_Medicament")]
    public class Prescription_Medicament
    {
        [Required]
        public int IdPrescription { get; set; }
        public Prescription Prescription { get; set; }
        [Required]
        public int IdMedicament { get; set; }
        public Medicament Medicament { get; set; }
        public int? Dose { get; set; }
        [Required]
        [MaxLength(100)]
        public string Details { get; set; }
    }
}
