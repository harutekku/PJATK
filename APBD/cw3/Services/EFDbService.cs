using cw3.DTOs.Requests;
using cw3.Models;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Claims;
using System.Threading.Tasks;

namespace cw3.Services
{
    public class EFDbService : IDbService
    {
        private readonly kubbitContext context;
        public EFDbService(kubbitContext kubbitContext)
        {
            context = kubbitContext;
        }

        public IEnumerable<Student> GetStudents()
        {
            return context.Student.ToList();
        }
        public void updateStudent(Student student)
        {
            var result = context.Student.SingleOrDefault(s => s.IndexNumber == student.IndexNumber);
            if (result != null)
            {
                result = student;
                context.SaveChanges();
            }
            context.Dispose();
        }
        public void deleteStudent(string id)
        {
            var student = context.Student.Where(s => s.IndexNumber == id).First();
            context.Student.Remove(student);
            context.SaveChanges();
            context.Dispose();
        }
        public void EnrollStudent(Student student)
        {
            if (!context.Enrollment.Any(x => x.IdEnrollment == student.IdEnrollment))
            {
                var enroll = new Enrollment
                {
                    IdStudy = 1,
                    Semester = 1,
                    StartDate = DateTime.Now
                };
                context.Add(enroll);
                context.SaveChanges();
                student.IdEnrollment = enroll.IdEnrollment;
            }
            context.Add(student);
            context.SaveChanges();
        }
        public void PromoteStudents(Enrollment enrollment)
        {
            var newEnrollment = new Enrollment
            {
                Semester = enrollment.Semester + 1,
                StartDate = DateTime.Now
            };
            context.Add(newEnrollment);
            foreach (var student in context.Student.Where(x => x.IdEnrollment == enrollment.IdEnrollment).ToList()) student.IdEnrollment = newEnrollment.IdEnrollment;
            context.SaveChanges();

        }
        public bool checkCredentials(string index, string password)
        {
            throw new NotImplementedException();
        }
        public bool checkIndex(string index)
        {
            return true;
            //var result = context.Student.SingleOrDefault(s => s.IndexNumber == index);
            //var res = context.Student.Find(index);
            //return res != null;
        }
        public IEnumerable<Claim> CheckTokenGiveClaims(string token)
        {
            throw new NotImplementedException();
        }
        public IEnumerable<Claim> GetClaims(string index)
        {
            throw new NotImplementedException();
        }
        public void passwordToHash(string index, string password, string salt, string hash)
        {
            throw new NotImplementedException();
        }
        public void saveToken(Guid token, string indexNumber)
        {
            throw new NotImplementedException();
        }
    }
}
