using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

#pragma warning disable CA1814 // Prefer jagged arrays over multidimensional

namespace ServerApp.Migrations
{
    /// <inheritdoc />
    public partial class initial : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DeleteData(
                table: "AspNetRoles",
                keyColumn: "Id",
                keyValue: "a32cb258-a7a9-48f6-8b4c-564474b1c65a");

            migrationBuilder.DeleteData(
                table: "AspNetRoles",
                keyColumn: "Id",
                keyValue: "d8a77e5a-2680-4931-bfaa-92e9ea4477e6");

            migrationBuilder.InsertData(
                table: "AspNetRoles",
                columns: new[] { "Id", "ConcurrencyStamp", "Discriminator", "Name", "NormalizedName" },
                values: new object[,]
                {
                    { "52c5ab97-2ba5-4e88-a4b7-29e6ad9e9570", null, "ApplicationUserRole", "Admin", "Admin" },
                    { "670cab7b-7d80-4bc5-a536-9b09c99f6335", null, "ApplicationUserRole", "User", "User" }
                });
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DeleteData(
                table: "AspNetRoles",
                keyColumn: "Id",
                keyValue: "52c5ab97-2ba5-4e88-a4b7-29e6ad9e9570");

            migrationBuilder.DeleteData(
                table: "AspNetRoles",
                keyColumn: "Id",
                keyValue: "670cab7b-7d80-4bc5-a536-9b09c99f6335");

            migrationBuilder.InsertData(
                table: "AspNetRoles",
                columns: new[] { "Id", "ConcurrencyStamp", "Discriminator", "Name", "NormalizedName" },
                values: new object[,]
                {
                    { "a32cb258-a7a9-48f6-8b4c-564474b1c65a", null, "ApplicationUserRole", "Admin", "Admin" },
                    { "d8a77e5a-2680-4931-bfaa-92e9ea4477e6", null, "ApplicationUserRole", "User", "User" }
                });
        }
    }
}
