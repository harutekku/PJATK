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
                .OnDelete(DeleteBehavior.Cascade)
                .HasConstraintName("PrescriptionMedicament_Prescription");

            modelBuilder.Entity<Prescription_Medicament>().HasOne(d => d.Medicament)
                .WithMany(e => e.Prescription_Medicament)
                .HasForeignKey(d => d.IdMedicament)
                .OnDelete(DeleteBehavior.Cascade)
                .HasConstraintName("PrescriptionMedicament_Medicament");


            var doctors = new List<Doctor>();
            doctors.Add(new Doctor
            {
                Id = 1,
                FirstName = "Kuba",
                LastName = "Ziemniak",
                Email = "kuba@ziemniak.com"
            });
            doctors.Add(new Doctor
            {
                Id = 2,
                FirstName = "Rafał",
                LastName = "Balut",
                Email = "rafal@balut.com"
            });

            var patients = new List<Patient>();
            patients.Add(new Patient
            {
                IdPatient = 1,
                FirstName = "Julian",
                LastName = "Mikołajczyk",
                BirthDate = DateTime.Parse("20-05-1990")
            });
            patients.Add(new Patient
            {
                IdPatient = 2,
                FirstName = "Julian",
                LastName = "Mikołajczyk",
                BirthDate = DateTime.Parse("02-01-1995")
            });
            var medicaments = new List<Medicament>();
            medicaments.Add(new Medicament
            {
                IdMedicament = 1,
                Name = "Paracetamol",
                Description = "Antivirus",
                Type = "Liquid"
            });
            medicaments.Add(new Medicament
            {
                IdMedicament = 2,
                Name = "Amol",
                Description = "Antivirus",
                Type = "Liquid"
            });
            var prescriptions = new List<Prescription>();
            prescriptions.Add(new Prescription
            {
                IdPrescription = 1,
                Date = DateTime.Parse("14-05-2020"),
                DueDate = DateTime.Parse("14-08-2020"),
                IdDoctor = 1,
                IdPatient = 1
            });
            prescriptions.Add(new Prescription
            {
                IdPrescription = 2,
                Date = DateTime.Parse("10-01-2020"),
                DueDate = DateTime.Parse("10-06-2020"),
                IdDoctor = 2,
                IdPatient = 2
            });
            
            var prescriptionMedicaments = new List<Prescription_Medicament>();
            prescriptionMedicaments.Add(new Prescription_Medicament
            {
                Dose = 10,
                Details = "2 razy dziennie",
                IdMedicament = 1,
                IdPrescription = prescriptions[0].IdDoctor
            });
            prescriptionMedicaments.Add(new Prescription_Medicament
            {
                Dose = 3,
                Details = "4 razy dziennie",
                IdMedicament = 2,
                IdPrescription = prescriptions[1].IdDoctor
            });

            modelBuilder.Entity<Doctor>().HasData(doctors);

            modelBuilder.Entity<Patient>().HasData(patients);

            modelBuilder.Entity<Medicament>().HasData(medicaments);

            modelBuilder.Entity<Prescription>().HasData(prescriptions);

            modelBuilder.Entity<Prescription_Medicament>().HasData(prescriptionMedicaments);
        }

    }
}
