using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using kolos2.DTOs;
using kolos2.Models;
using kolos2.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace kolos2.Controllers
{
    [Route("api/championships")]
    [ApiController]
    public class ChampionshipController : ControllerBase
    {
        private readonly IChampionshipService championshipService;

        public ChampionshipController(IChampionshipService championshipService)
        {
            this.championshipService = championshipService;
        }

        [HttpGet("{idChampionship}/teams")]
        public IActionResult GetTeams(int IdChampionship)
        {
            var result = championshipService.getTeamsByChampionship(IdChampionship);
            if (result == null || !result.Any())
            {
                return NotFound("Teams in championships not found");
            }
            return Ok(result);
        }
    }
}
