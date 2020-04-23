using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using cw3.DTOs.Requests;
using cw3.Services;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.IdentityModel.Tokens;

namespace cw3.Controllers
{
    [ApiController]
    [Route("api/login")]
    public class LoginController : Controller
    {
        private IDbService _service;
        private IConfiguration _configuration;

        public LoginController(IDbService service, IConfiguration configuration)
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
                expires: DateTime.Now.AddMinutes(100),
                signingCredentials: creds
            );
            var refreshToken = Guid.NewGuid();
            _service.saveToken(refreshToken, request.index);
            return Ok(new
            {
                accesstoken = new JwtSecurityTokenHandler().WriteToken(token),
                refreshToken
            });
        }
        [HttpPost("refresh/{refreshtoken}")]
        public IActionResult RefreshToken(string refreshToken)
        {
            try
            {
                var claims = _service.CheckTokenGiveClaims(refreshToken);
                var key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(_configuration["SecretKey"]));
                var creds = new SigningCredentials(key, SecurityAlgorithms.HmacSha256);
                var token = new JwtSecurityToken(
                    issuer: "kubbit",
                    audience: "Students",
                    claims: claims,
                    expires: DateTime.Now.AddMinutes(100),
                    signingCredentials: creds
                );
                return Ok(new
                {
                    accesstoken = new JwtSecurityTokenHandler().WriteToken(token),
                    refreshToken
                });
            }
            catch (UnauthorizedAccessException)
            {
                return Unauthorized("Fake refresh token");
            }
        }
    }
}