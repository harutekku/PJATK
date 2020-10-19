using Microsoft.EntityFrameworkCore.Migrations;

namespace cw11.Migrations
{
    public partial class UpdatePrescriptionMedicamentTable : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AlterColumn<int>(
                name: "Dose",
                table: "Prescription_Medicament",
                nullable: true,
                oldClrType: typeof(int),
                oldType: "int");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AlterColumn<int>(
                name: "Dose",
                table: "Prescription_Medicament",
                type: "int",
                nullable: false,
                oldClrType: typeof(int),
                oldNullable: true);
        }
    }
}
