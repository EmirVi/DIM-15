using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using ServerApp.Entities;

namespace ServerApp.Data
{
    public class ApplicationDBContext : IdentityDbContext<ApplicationUser>
    {
        public ApplicationDBContext(DbContextOptions options) : base(options)
        {
        }

        protected override void OnModelCreating(ModelBuilder builder)
        {
            base.OnModelCreating(builder);
            builder.Entity<ApplicationUserRole>().HasData(
                new ApplicationUserRole { Name = Utilities.Roles.User.ToString(), NormalizedName = Utilities.Roles.User.ToString() });
            builder.Entity<ApplicationUserRole>().HasData(
                new ApplicationUserRole { Name = Utilities.Roles.Admin.ToString(), NormalizedName = Utilities.Roles.Admin.ToString() });
        }
    }
}