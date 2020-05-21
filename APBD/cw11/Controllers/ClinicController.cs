using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using cw11.Models;
using cw11.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace cw11.Controllers
{
    [Route("api/clinic")]
    [ApiController]
    public class ClinicController : ControllerBase
    {
        private readonly ClinicDbContext _context;
        private readonly IClinicDbService _service;
        public ClinicController(ClinicDbContext clinicDbContext) { _context = clinicDbContext; }
        public ClinicController(IClinicDbService iClinicDbService) { _service = iClinicDbService; }
        [HttpGet]
        public IActionResult GetDoctors() { return Ok(_service.GetDoctors()); }
        [HttpGet("{id}")]
        public Doctor Get(int id) { return _service.GetDoctor(id); }
        [HttpPost]
        public void Post([FromBody]Doctor doctor) { try { _service.CreateDoctor(doctor); } catch (Exception) { } }
        [HttpPut("{id}")]
        public void Put(int id, [FromBody]Doctor doctor) { try { _service.ChangeDoctor(id, doctor); } catch (Exception) { } }
        [HttpDelete("{id}")]
        public void Delete(int id) { try { _service.DeleteDoctor(id); } catch (Exception) { } }
    }
}