// PJC12.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include "pch.h"
#include <iostream>

#include <SFML/Graphics.hpp>
#include <SFML/Window.hpp>

int main()
{
	srand(time(NULL));
	sf::RenderWindow window(sf::VideoMode(400, 400), "SFML works!");
	sf::CircleShape shape(100.f);
	sf::CircleShape circle1(30);
	sf::CircleShape circle2(30);
	sf::CircleShape circle3(30);
	sf::CircleShape circle4(30);
	shape.setFillColor(sf::Color::Green);
	circle1.setPosition(rand() % 370, rand() % 370);
	circle2.setPosition(rand() % 370, rand() % 370);
	circle3.setPosition(rand() % 370, rand() % 370);
	circle4.setPosition(rand() % 370, rand() % 370);
	circle1.setFillColor(sf::Color::Blue);
	circle2.setFillColor(sf::Color::Red);
	circle3.setFillColor(sf::Color::Green);
	circle4.setFillColor(sf::Color::Yellow);
	float a = 0.05, b = 0.09, c = 0.12;
	while (window.isOpen())
	{
		sf::Event event;
		while (window.pollEvent(event))
		{
			if (event.type == sf::Event::Closed)
				window.close();
			if (event.key.code == sf::Keyboard::Enter) {
				window.create(sf::VideoMode(400, 400), "SFML works!", sf::Style::Fullscreen);
				//std::cout << "enter" << std::endl;
			}
			if (event.key.code == sf::Keyboard::Escape) {
				window.create(sf::VideoMode(400, 400), "SFML works!", sf::Style::Default);
				//std::cout << "escape" << std::endl;
			}
		}

		window.clear();
		window.draw(circle1);
		window.draw(circle2);
		window.draw(circle3);
		window.draw(circle4);
		window.display();
		circle1.move(a,b);
		if (circle1.getPosition().x > 360)a=abs(a)*-1;
		else if (circle1.getPosition().x < 0)a = abs(a);
		if (circle1.getPosition().y > 360)b = abs(b)*-1;
		else if (circle1.getPosition().y < 0)b = abs(b);
		
	}

	return 0;
}

// Run program: Ctrl + F5 or Debug > Start Without Debugging menu
// Debug program: F5 or Debug > Start Debugging menu

// Tips for Getting Started: 
//   1. Use the Solution Explorer window to add/manage files
//   2. Use the Team Explorer window to connect to source control
//   3. Use the Output window to see build output and other messages
//   4. Use the Error List window to view errors
//   5. Go to Project > Add New Item to create new code files, or Project > Add Existing Item to add existing code files to the project
//   6. In the future, to open this project again, go to File > Open > Project and select the .sln file
