using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using kolos2.DTOs;
using kolos2.Exceptions;
using kolos2.Models;
using kolos2.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace kolos2.Controllers
{
    [Route("api/teams")]
    [ApiController]
    public class TeamsController : ControllerBase
    {
        private readonly IChampionshipService championshipService;

        public TeamsController(IChampionshipService championshipService)
        {
            this.championshipService = championshipService;
        }
        [HttpPost("{IdTeam}/players")]
        public IActionResult AddPlayerToTeam(int IdTeam, AddPlayerRequest addPlayerRequest)
        {
            try
            {
                championshipService.addPlayerToTeam(addPlayerRequest, IdTeam);
                return Ok("Player added");
            }
            catch (NoPlayerException e)
            {
                return BadRequest(e);
            }
            catch(PlayerAreadyInTeamException e)
            {
                return BadRequest(e);
            }
            catch(TooOldException e)
            {
                return BadRequest(e);
            }
        }
    }
}
