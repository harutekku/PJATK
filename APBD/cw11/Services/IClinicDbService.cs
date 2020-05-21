using cw11.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace cw11.Services
{
    public interface IClinicDbService
    {
        public IEnumerable<Doctor> GetDoctors();
        public Doctor GetDoctor(int id);
        public void CreateDoctor(Doctor doctor);
        public void ChangeDoctor(int id, Doctor doctor);
        public void DeleteDoctor(int id);
    }
}
