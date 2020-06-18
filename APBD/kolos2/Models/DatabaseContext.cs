using kolos2.Configurations;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace kolos2.Models
{
    public class DatabaseContext : DbContext
    {
        public DbSet<Player> Player { get; set; }
        public DbSet<Player_Team> Player_Team { get; set; }
        public DbSet<Team> Team { get; set; }
        public DbSet<Championship_Team> Championship_Team { get; set; }
        public DbSet<Championship> Championship { get; set; }
        public DatabaseContext()
        {

        }
        public DatabaseContext(DbContextOptions<DatabaseContext> options) : base(options)
        {

        }
        protected override void OnModelCreating(ModelBuilder modelbuilder)
        {
            modelbuilder.ApplyConfiguration(new PlayerConfiguration());
            modelbuilder.ApplyConfiguration(new Player_TeamConfiguration());
            modelbuilder.ApplyConfiguration(new TeamConfiguration());
            modelbuilder.ApplyConfiguration(new Championship_TeamConfiguration());
            modelbuilder.ApplyConfiguration(new ChampionshipConfiguration());

            var Player = new List<Player>();
            var Player_Team = new List<Player_Team>();
            var Team = new List<Team>();
            var Championship_Team = new List<Championship_Team>();
            var Championship = new List<Championship>();
            Player.Add(new Player
            {
                IdPlayer = 1,
                FirstName = "Jan",
                LastName = "Biniek",
                DateOfBirth = DateTime.Parse("12-12-1997")
            });
            Player.Add(new Player
            {
                IdPlayer = 2,
                FirstName = "Kuba",
                LastName = "Adamczyk",
                DateOfBirth = DateTime.Parse("12-12-1997")
            });
            Player.Add(new Player
            {
                IdPlayer = 3,
                FirstName = "Kacper",
                LastName = "Urbański",
                DateOfBirth = DateTime.Parse("12-12-1997")
            });
            Player.Add(new Player
            {
                IdPlayer = 4,
                FirstName = "Rafał",
                LastName = "Piórek",
                DateOfBirth = DateTime.Parse("12-12-1997")
            });
            modelbuilder.Entity<Player>().HasData(Player);

            Team.Add(new Team
            {
                IdTeam = 1,
                TeamName = "Groove",
                MaxAge = 30
            });
            Team.Add(new Team
            {
                IdTeam = 2,
                TeamName = "Beksy",
                MaxAge = 30
            });
            modelbuilder.Entity<Team>().HasData(Team);

            Championship.Add(new Championship
            {
                IdChampionship = 1,
                OfficialName = "LM",
                Year = 2019
            });
            Championship.Add(new Championship
            {
                IdChampionship = 2,
                OfficialName = "LMS",
                Year = 2020
            });
            modelbuilder.Entity<Championship>().HasData(Championship);

            Player_Team.Add(new Player_Team
            {
                IdPlayerTeam = 1,
                IdPlayer = 1,
                IdTeam = 1,
                NumOnShirt = 1,
                Comment = "mlody"
            });
            Player_Team.Add(new Player_Team
            {
                IdPlayerTeam = 2,
                IdPlayer = 2,
                IdTeam = 1,
                NumOnShirt = 2,
                Comment = "stary"
            }); 
            Player_Team.Add(new Player_Team
            {
                IdPlayerTeam = 3,
                IdPlayer = 3,
                IdTeam = 2,
                NumOnShirt = 1,
                Comment = "mlody"
            });
            Player_Team.Add(new Player_Team
            {
                IdPlayerTeam = 4,
                IdPlayer = 4,
                IdTeam = 2,
                NumOnShirt = 2,
                Comment = "stary"
            });
            modelbuilder.Entity<Player_Team>().HasData(Player_Team);

            Championship_Team.Add(new Championship_Team
            {
                IdChampionshipTeam = 1,
                IdTeam = 1,
                IdChampionship = 1,
                Score = 5
            });
            Championship_Team.Add(new Championship_Team
            {
                IdChampionshipTeam = 2,
                IdTeam = 2,
                IdChampionship = 1,
                Score = 3
            });
            Championship_Team.Add(new Championship_Team
            {
                IdChampionshipTeam = 3,
                IdTeam = 1,
                IdChampionship = 2,
                Score = 3
            });
            Championship_Team.Add(new Championship_Team
            {
                IdChampionshipTeam = 4,
                IdTeam = 2,
                IdChampionship = 2,
                Score = 5
            });
            modelbuilder.Entity<Championship_Team>().HasData(Championship_Team);
        }
    }
}
