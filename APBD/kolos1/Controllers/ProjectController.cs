using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using kolos1.DTOs;
using kolos1.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace kolos1.Controllers
{
    [Route("api/project")]
    [ApiController]
    public class ProjectController : ControllerBase
    {
        private readonly IDbService _dbService;
        public ProjectController(IDbService dbService)
        {
            _dbService = dbService;
        }
        [HttpGet("{id}")]
        public IActionResult getProject(string id)
        {
            //try
            //{
                var response = _dbService.getTasks(id);
                return Ok(response);
            //}
            //catch (Exception)
            //{
            //    return NotFound();
            //}
        }
        [HttpPost]
        public IActionResult addProject(TaskRequest taskRequest)
        {
            try
            {
                _dbService.addTask(taskRequest);
            }
            catch (Exception)
            {
                return BadRequest("Zle dane");
            }
            return Ok("Dodano");
        }
    }
}