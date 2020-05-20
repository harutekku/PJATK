using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace cw11.Models
{
    public class ClinicDbContext : DbContext
    {
        public DbSet<Doctor> Doctors { get; set; }
        public DbSet<Medicament> Medicaments { get; set; }
        public DbSet<Patient> Patients { get; set; }
        public DbSet<Prescription> Prescriptions { get; set; }
        public DbSet<Prescription_Medicament> Prescription_Medicaments{ get; set; }
        public ClinicDbContext() { }
        public ClinicDbContext(DbContextOptions options) : base(options) { }
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Prescription_Medicament>().HasKey(e => new { e.IdPrescription, e.IdMedicament })
                .HasName("Prescription_Medicament_PK");
            modelBuilder.Entity<Prescription_Medicament>().HasOne(d => d.Prescription)
                .WithMany(e => e.Prescription_Medicament)
                .HasForeignKey(d => d.IdPrescription)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("PrescriptionMedicament_Prescription");

            modelBuilder.Entity<Prescription_Medicament>().HasOne(d => d.Medicament)
                .WithMany(e => e.Prescription_Medicament)
                .HasForeignKey(d => d.IdMedicament)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("PrescriptionMedicament_Medicament");
            //
        }

    }
}
