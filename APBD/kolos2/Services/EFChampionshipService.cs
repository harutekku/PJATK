using kolos2.DTOs;
using kolos2.Exceptions;
using kolos2.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace kolos2.Services
{
    public class EFChampionshipService : IChampionshipService
    {
        private readonly DatabaseContext databaseContext;
        public EFChampionshipService(DatabaseContext databaseContext)
        {
            this.databaseContext = databaseContext;
        }
        public void addPlayerToTeam(AddPlayerRequest addPlayerRequest, int team)
        {
            if (!databaseContext.Player.Where(e => e.FirstName == addPlayerRequest.firstName && e.LastName == addPlayerRequest.lastName && e.DateOfBirth == addPlayerRequest.birthdate).Any())
            {
                throw new NoPlayerException("No player found");
            }
            if (DateTime.Now.Year - addPlayerRequest.birthdate.Year >= databaseContext.Team.Where(e => e.IdTeam == team).Select(e => e.MaxAge).FirstOrDefault())
            {
                throw new TooOldException("Player is too old");
            }
            if (databaseContext.Player_Team.Include(i => i.Player).Where(e => e.Player.FirstName == addPlayerRequest.firstName && e.Player.LastName == addPlayerRequest.lastName && e.Player.DateOfBirth == addPlayerRequest.birthdate && e.IdTeam == team).Select(e => e.IdTeam).Any())
            {
                throw new PlayerAreadyInTeamException("Player already exists in this team");
            }
            databaseContext.Add(new Player_Team
            {
                NumOnShirt = addPlayerRequest.numOnShirt,
                Comment = addPlayerRequest.comment,
                IdTeam = team,
                IdPlayer = databaseContext.Player.Where(e => e.FirstName == addPlayerRequest.firstName && e.LastName == addPlayerRequest.lastName && e.DateOfBirth == addPlayerRequest.birthdate).Select(e => e.IdPlayer).FirstOrDefault()
            });
            databaseContext.SaveChanges();
        }

        public IEnumerable<object> getTeamsByChampionship(int IdChampionship)
        {
            return databaseContext.Championship_Team.Include(e => e.Team).Where(e => e.IdChampionship == IdChampionship).Select(e => new TeamResponse
            {
                IdTeam = e.IdTeam,
                TeamName = e.Team.TeamName,
                MaxAge = e.Team.MaxAge,
                Score = e.Score
            }).OrderBy(e => e.Score).ToList();
        }
    }
}
