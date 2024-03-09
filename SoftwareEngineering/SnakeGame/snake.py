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

# Функция отрисовки змейки
def our_snake(snake_block, snake_list):
    for x in snake_list:
        pygame.draw.rect(dis, black, [x[0], x[1], snake_block, snake_block])

# Функция отрисовки счета
def Your_score(score):
    value = font_style.render("Ваш счет: " + str(score), True, blue)
    dis.blit(value, [0, 0])

# Основная функция игры
def gameLoop():
    game_over = False

    x1 = dis_width / 2
    y1 = dis_height / 2

    x1_change = 0
    y1_change = 0

    while not game_over:

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                game_over = True
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_LEFT:
                    x1_change = -snake_block
                    y1_change = 0
                elif event.key == pygame.K_RIGHT:
                    x1_change = snake_block
                    y1_change = 0
                elif event.key == pygame.K_UP:
                    y1_change = -snake_block
                    x1_change = 0
                elif event.key == pygame.K_DOWN:
                    y1_change = snake_block
                    x1_change = 0

        x1 += x1_change
        y1 += y1_change
        dis.fill(blue)
        pygame.draw.rect(dis, green, [x1, y1, snake_block, snake_block])

        pygame.display.update()

        pygame.time.Clock().tick(snake_speed)

    pygame.quit()
    quit()

# Запуск игрового цикла
gameLoop()