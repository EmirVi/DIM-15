using Microsoft.AspNetCore.Identity;

namespace ServerApp.Entities
{
    public class ApplicationUserRole : IdentityRole
    {
        public ApplicationUserRole() : base()
        {

        }
        public ApplicationUserRole(string roleName) : base(roleName)
        {

        }
    }
}