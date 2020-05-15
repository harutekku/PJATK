using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using cw3.DTOs.Requests;
using cw3.Models;
using cw3.Services;
using Microsoft.AspNetCore.Mvc;

namespace cw3.Controllers
{
    [ApiController]
    [Route("api/cw10")]
    public class cw10Controller : ControllerBase
    {
        private readonly IDbService _dbService;
        public cw10Controller(IDbService dbService)
        {
            _dbService = dbService;
        }
        [HttpGet]
        public IActionResult getStudents()
        {
            return Ok(_dbService.GetStudents());
        }
        [HttpPost]
        public IActionResult updateStudent(Student student)
        {
            _dbService.updateStudent(student);
            return Ok();
        }
        [HttpDelete("{id}")]
        public IActionResult deleteStudent(string id)
        {
            _dbService.deleteStudent(id);
            return Ok();
        }
        [HttpPost("enroll")]
        public IActionResult EnrollStudent(Student request)
        {
            _dbService.EnrollStudent(request);
            return Ok();
        }

        [HttpPost("promote")]
        public IActionResult PromoteStudents(Enrollment promotionRequest)
        {
            _dbService.PromoteStudents(promotionRequest);
            return Ok();
        }
    }
}