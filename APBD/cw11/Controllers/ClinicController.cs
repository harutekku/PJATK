using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using cw11.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace cw11.Controllers
{
    [Route("api/clinic")]
    [ApiController]
    public class ClinicController : ControllerBase
    {
        private readonly ClinicDbContext context;
        public ClinicController(ClinicDbContext clinicDbContext)
        {
            context = clinicDbContext;

        }
        [HttpGet]
        public IActionResult GetDoctors()
        {
            return Ok();
        }
    }
}