using ServerApp.Entities;

namespace ServerApp.Interfaces
{
    public interface ITokenService
    {
        Task<string> CreateToken(ApplicationUser user);
    }
}