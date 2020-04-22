using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using cw3.DTOs.Requests;
using cw3.Models;
using cw3.Services;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.IdentityModel.Tokens;

namespace cw3.Controllers
{
    [ApiController]
    [Route("api/enrollments")]
    [Authorize(Roles = "Employee")]
    public class EnrollmentsController : Controller
    {
        private IDbService _service;

        public EnrollmentsController(IDbService service)
        {
            _service = service;
        }

        [HttpPost]
        public IActionResult EnrollStudent(EnrollStudentRequest request)
        {
            return _service.EnrollStudent(request);
        }

        [HttpPost("promotions")]
        public IActionResult PromoteStudents(PromotionRequest promotionRequest)
        {
            return _service.PromoteStudents(promotionRequest);
        }
    }
}