using cw11.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace cw11.Services
{
    public class SqlClinicDbService : IClinicDbService
    {
        private readonly ClinicDbContext _context;

        public SqlClinicDbService(ClinicDbContext clinicDbContext)
        {
            _context = clinicDbContext;
        }
        public IEnumerable<Doctor> GetDoctors()
        {
            return _context.Doctors.ToList();
        }
        public Doctor GetDoctor(int id)
        {
            return _context.Doctors.Where(d => d.Id == id).SingleOrDefault();
        }
        public void CreateDoctor(Doctor doctor)
        {
            _context.Doctors.Add(doctor);
            _context.SaveChanges();
        }
        public void ChangeDoctor(int id, Doctor doctor)
        {
            var d = _context.Doctors.Where(d => d.Id == id).SingleOrDefault();
            d.FirstName = doctor.FirstName;
            d.LastName = doctor.LastName;
            d.Email = doctor.Email;
            _context.SaveChanges();
        }
        public void DeleteDoctor(int id)
        {
            var doctor = GetDoctor(id);
            _context.Doctors.Remove(doctor);
            _context.SaveChanges();
        }


    }
}
