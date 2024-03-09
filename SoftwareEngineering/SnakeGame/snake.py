import pygame
import time
import random

# Инициализация Pygame
pygame.init()

# Определение цветов
black = (0, 0, 0)
red = (213, 50, 80)
green = (0, 255, 0)
blue = (50, 153, 213)

# Установка размеров экрана
dis_width = 800
dis_height = 600
dis = pygame.display.set_mode((dis_width, dis_height))
pygame.display.set_caption('Змейка')

# Установка параметров игрового процесса
snake_block = 10
snake_speed = 15

# Определение шрифта и размера текста
font_style = pygame.font.SysFont(None, 50)