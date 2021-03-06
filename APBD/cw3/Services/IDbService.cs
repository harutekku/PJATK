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
    public interface IDbService
    {
        void EnrollStudent(Student request);
        void PromoteStudents(Enrollment request);
        bool checkIndex(string index);
        bool checkCredentials(string index, string password);
        IEnumerable<Claim> GetClaims(string index);
        IEnumerable<Claim> CheckTokenGiveClaims(string token);
        void saveToken(Guid token, string indexNumber);
        void passwordToHash(string index, string password, string salt, string hash);
        IEnumerable<Student> GetStudents();
        void updateStudent(Student student);
        void deleteStudent(string id);
    }
}
