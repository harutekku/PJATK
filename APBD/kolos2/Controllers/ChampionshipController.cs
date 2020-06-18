using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using kolos2.Models;
using kolos2.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace kolos2.Controllers
{
    [Route("api/")]
    [ApiController]
    public class ChampionshipController : ControllerBase
    {
        private readonly IChampionshipService championshipService;

        public ChampionshipController(IChampionshipService championshipService)
        {
            this.championshipService = championshipService;
        }

        [HttpGet("championships/{idChampionship}/teams}")]
        public IActionResult GetTeams(int IdChampionship)
        {
            try
            {
                return Ok(championshipService.getTeamsByChampionship(IdChampionship));
            }
            catch (Exception e)
            {
                return NotFound(e);
            }
        }
        //powinienem mieć dwa oddzielne kontrolery ale nie zdąże już :C

        [HttpPost("teams/{IdTeam}/players")]
        public IActionResult CreateOrder(int IdTeam, Player player)
        {
            try
            {
                championshipService.addPlayerToTeam(player, IdTeam);
                return Ok();
            }
            catch (Exception e)
            {
                return BadRequest(e);
            }
        }
    }
}
