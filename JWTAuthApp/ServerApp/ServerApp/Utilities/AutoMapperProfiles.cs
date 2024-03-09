using AutoMapper;
using ServerApp.DTO;
using ServerApp.Entities;

namespace ServerApp.Utilities
{
    public class AutoMapperProfiles : Profile
    {
        public AutoMapperProfiles()
        {
            CreateMap<RegisterDTO, ApplicationUser>()
                 .ForMember(dest => dest.UserName, opt => opt.MapFrom(src => src.UserName));
        }
    }
}