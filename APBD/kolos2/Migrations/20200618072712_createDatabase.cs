using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace kolos2.Migrations
{
    public partial class createDatabase : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Championship",
                columns: table => new
                {
                    IdChampionship = table.Column<int>(nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    OfficialName = table.Column<string>(maxLength: 100, nullable: false),
                    Year = table.Column<int>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("IdChampionship", x => x.IdChampionship);
                });

            migrationBuilder.CreateTable(
                name: "Player",
                columns: table => new
                {
                    IdPlayer = table.Column<int>(nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    FirstName = table.Column<string>(maxLength: 30, nullable: false),
                    LastName = table.Column<string>(maxLength: 50, nullable: false),
                    DateOfBirth = table.Column<DateTime>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("IdPlayer", x => x.IdPlayer);
                });

            migrationBuilder.CreateTable(
                name: "Team",
                columns: table => new
                {
                    IdTeam = table.Column<int>(nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    TeamName = table.Column<string>(maxLength: 30, nullable: false),
                    MaxAge = table.Column<int>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("IdTeam", x => x.IdTeam);
                });

            migrationBuilder.CreateTable(
                name: "Championship_Team",
                columns: table => new
                {
                    IdChampionshipTeam = table.Column<int>(nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    IdTeam = table.Column<int>(nullable: false),
                    IdChampionship = table.Column<int>(nullable: false),
                    Score = table.Column<float>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("IdChampionshipTeam", x => x.IdChampionshipTeam);
                    table.ForeignKey(
                        name: "Championship_Team_Championship",
                        column: x => x.IdChampionship,
                        principalTable: "Championship",
                        principalColumn: "IdChampionship",
                        onDelete: ReferentialAction.Restrict);
                    table.ForeignKey(
                        name: "Championship_Team_Team",
                        column: x => x.IdTeam,
                        principalTable: "Team",
                        principalColumn: "IdTeam",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.CreateTable(
                name: "Player_Team",
                columns: table => new
                {
                    IdPlayerTeam = table.Column<int>(nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    IdPlayer = table.Column<int>(nullable: false),
                    IdTeam = table.Column<int>(nullable: false),
                    NumOnShirt = table.Column<int>(maxLength: 300, nullable: false),
                    Comment = table.Column<string>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("IdPlayerTeam", x => x.IdPlayerTeam);
                    table.ForeignKey(
                        name: "Player_Team_Player",
                        column: x => x.IdPlayer,
                        principalTable: "Player",
                        principalColumn: "IdPlayer",
                        onDelete: ReferentialAction.Restrict);
                    table.ForeignKey(
                        name: "Player_Team_Team",
                        column: x => x.IdTeam,
                        principalTable: "Team",
                        principalColumn: "IdTeam",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.InsertData(
                table: "Championship",
                columns: new[] { "IdChampionship", "OfficialName", "Year" },
                values: new object[,]
                {
                    { 1, "LM", 2019 },
                    { 2, "LMS", 2020 }
                });

            migrationBuilder.InsertData(
                table: "Player",
                columns: new[] { "IdPlayer", "DateOfBirth", "FirstName", "LastName" },
                values: new object[,]
                {
                    { 1, new DateTime(1997, 12, 12, 0, 0, 0, 0, DateTimeKind.Unspecified), "Jan", "Biniek" },
                    { 2, new DateTime(1997, 12, 12, 0, 0, 0, 0, DateTimeKind.Unspecified), "Kuba", "Adamczyk" },
                    { 3, new DateTime(1997, 12, 12, 0, 0, 0, 0, DateTimeKind.Unspecified), "Kacper", "Urbański" },
                    { 4, new DateTime(1997, 12, 12, 0, 0, 0, 0, DateTimeKind.Unspecified), "Rafał", "Piórek" }
                });

            migrationBuilder.InsertData(
                table: "Team",
                columns: new[] { "IdTeam", "MaxAge", "TeamName" },
                values: new object[,]
                {
                    { 1, 30, "Groove" },
                    { 2, 30, "Beksy" }
                });

            migrationBuilder.InsertData(
                table: "Championship_Team",
                columns: new[] { "IdChampionshipTeam", "IdChampionship", "IdTeam", "Score" },
                values: new object[,]
                {
                    { 1, 1, 1, 5f },
                    { 3, 2, 1, 3f },
                    { 2, 1, 2, 3f },
                    { 4, 2, 2, 5f }
                });

            migrationBuilder.InsertData(
                table: "Player_Team",
                columns: new[] { "IdPlayerTeam", "Comment", "IdPlayer", "IdTeam", "NumOnShirt" },
                values: new object[,]
                {
                    { 1, "mlody", 1, 1, 1 },
                    { 2, "stary", 2, 1, 2 },
                    { 3, "mlody", 3, 2, 1 },
                    { 4, "stary", 4, 2, 2 }
                });

            migrationBuilder.CreateIndex(
                name: "IX_Championship_Team_IdChampionship",
                table: "Championship_Team",
                column: "IdChampionship");

            migrationBuilder.CreateIndex(
                name: "IX_Championship_Team_IdTeam",
                table: "Championship_Team",
                column: "IdTeam");

            migrationBuilder.CreateIndex(
                name: "IX_Player_Team_IdPlayer",
                table: "Player_Team",
                column: "IdPlayer");

            migrationBuilder.CreateIndex(
                name: "IX_Player_Team_IdTeam",
                table: "Player_Team",
                column: "IdTeam");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Championship_Team");

            migrationBuilder.DropTable(
                name: "Player_Team");

            migrationBuilder.DropTable(
                name: "Championship");

            migrationBuilder.DropTable(
                name: "Player");

            migrationBuilder.DropTable(
                name: "Team");
        }
    }
}
