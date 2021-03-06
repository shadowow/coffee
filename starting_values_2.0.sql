﻿INSERT INTO drink (id, "name", picture, price, count, note, volume, hot) VALUES
	((select nextval('product_id_seq')), 'Латте','/resources/photos/1.jpg',150,-1,'Кофе эспрессо с большим количеством горячего вспененного молока. Всегда впечатляет красивой белой пенкой.',0.4,true),
	((select nextval('product_id_seq')), 'Латте','/resources/photos/1.jpg',120,-1,'Кофе эспрессо с большим количеством горячего вспененного молока. Всегда впечатляет красивой белой пенкой.',0.3,true),
	((select nextval('product_id_seq')), 'Фраппе мокко','/resources/photos/4.jpg',150,-1,'Нежный напиток мокко со льдом и взбитыми сливками.',0.4,false),
	((select nextval('product_id_seq')), 'Горячий шоколад','/resources/photos/5.jpg',120,-1,'Горячий напиток на основе вспененного молока и шоколадной пудры, украшенный взбитыми сливками.',0.4,true),
	((select nextval('product_id_seq')), 'Капучино','/resources/photos/6.jpg',120,-1,'Кофе эспрессо с горячим вспененным молоком, который мы любим за густую пенку в нашей чашке. Добавьте корицу или шоколад по вкусу.',0.3,true),
	((select nextval('product_id_seq')), 'Капучино','/resources/photos/6.jpg',150,-1,'Кофе эспрессо с горячим вспененным молоком, который мы любим за густую пенку в нашей чашке. Добавьте корицу или шоколад по вкусу.',0.4,true),
	((select nextval('product_id_seq')), 'Фраппе латте','/resources/photos/7.jpg',150,-1,'Нежный напиток латте со льдом и взбитыми сливками.',0.4,false);
INSERT INTO status VALUES
	((select nextval('status_id_seq')),'Ожидание подтверждения'),
	((select nextval('status_id_seq')),'Приготовление'),
	((select nextval('status_id_seq')),'В очереди'),
	((select nextval('status_id_seq')),'Ожидание отправки'),
	((select nextval('status_id_seq')),'В пути');
INSERT INTO bakery (id, "name", picture, price, count, note, weight, "date") VALUES
	((select nextval('product_id_seq')),'Пряная вишня','/resources/photos/3.jpg',100,5,'Мусс пломбир, вишневая начинка с корицей и апельсином, миндальный бисквит с вишней.', 100,'2016-02-02 10:23:54'),
	((select nextval('product_id_seq')),'Чизкейк классический','/resources/photos/2.jpg',150,12,'Классический чизкейк из сливочного сыра с добавлением натуральной ванили.',120,'2016-02-02 10:23:54'),
	((select nextval('product_id_seq')),'Малиновый капкейк','/resources/photos/8.jpg',70,24,'Малиновый капкейк с малиновой начинкой и легкой малиновой белковой шапкой.',50,'2016-02-02 10:23:54');
INSERT INTO desert (id, "name", picture, price, count, note, weight, "firm") VALUES
	((select nextval('product_id_seq')),'Ассорти оригинальное','/resources/photos/9.png',250,3,'Пять любимых тортов в миниатюре: "Медовый", "Лесная сказка", "Кофейный", "Ореховый", "Прага" коллекционерам кулинарных шедевров.',550,'ТМ "У Палыча"');
