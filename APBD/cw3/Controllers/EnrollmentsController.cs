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
    public class EnrollmentsController : Controller
    {
        private IDbService _service;
        public IConfiguration _configuration;

        public EnrollmentsController(IDbService service, IConfiguration configuration)
        {
            _service = service;
            _configuration = configuration;
        }
        [HttpPost]
        public IActionResult Login(LoginRequest request)
        {
            if (!_service.checkCredentials(request.index, request.password)) return Unauthorized("Bad credentials");
            var claims = _service.GetClaims(request.index);
            var key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(_configuration["SecretKey"]));
            var creds = new SigningCredentials(key, SecurityAlgorithms.HmacSha256);
            var token = new JwtSecurityToken(
                issuer: "kubbit",
                audience: "Students",
                claims: claims,
                expires: DateTime.Now.AddMinutes(10),
                signingCredentials: creds
            );
            return Ok(new
            {
                accesstoken = new JwtSecurityTokenHandler().WriteToken(token),
                refreshToken = Guid.NewGuid()
            });
        }

        [HttpPost]
        [Authorize(Roles = "Employee")]
        public IActionResult EnrollStudent(EnrollStudentRequest request)
        {
            return _service.EnrollStudent(request);
        }
        [HttpPost("promotions")]
        [Authorize(Roles = "Employee")]
        public IActionResult PromoteStudents(PromotionRequest promotionRequest)
        {
            return _service.PromoteStudents(promotionRequest);
        }
    }
}