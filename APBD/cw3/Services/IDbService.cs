using cw3.DTOs.Requests;
using cw3.Models;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace cw3.Services
{
    public interface IDbService
    {
        IEnumerable<Student> GetStudents();
        IActionResult EnrollStudent(EnrollStudentRequest request);
        IActionResult PromoteStudents(PromotionRequest request);

        bool checkIndex(string index);
    }
}
