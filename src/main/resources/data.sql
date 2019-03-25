INSERT INTO ingrediente values (nextval('seq_ingrediente'), 'Alface', 0.40); --1
INSERT INTO ingrediente values (nextval('seq_ingrediente'), 'Bacon', 2.00); --2
INSERT INTO ingrediente values (nextval('seq_ingrediente'), 'Hambúrguer', 3.00); --3
INSERT INTO ingrediente values (nextval('seq_ingrediente'), 'Ovo', 0.80); --4
INSERT INTO ingrediente values (nextval('seq_ingrediente'), 'Queijo', 1.50); --5

INSERT INTO lanche values (nextval('seq_lanche'), 'X-Bacon', null); --1
INSERT INTO lanche values (nextval('seq_lanche'), 'X-Burguer', 'LIGHT'); --2
INSERT INTO lanche values (nextval('seq_lanche'), 'X-Egg', 'MUITA_CARNE'); --3
INSERT INTO lanche values (nextval('seq_lanche'), 'X-Egg Bacon', 'MUITO_QUEIJO'); --4

INSERT INTO ingrediente_lanche values (1, 2); --Bacon
INSERT INTO ingrediente_lanche values (1, 3); --Hambúrguer
INSERT INTO ingrediente_lanche values (1, 5); --Queijo

INSERT INTO ingrediente_lanche values (2, 3); --Hambúrguer
INSERT INTO ingrediente_lanche values (2, 5); --Queijo

INSERT INTO ingrediente_lanche values (3, 4); --Ovo
INSERT INTO ingrediente_lanche values (3, 3); --Hambúrguer
INSERT INTO ingrediente_lanche values (3, 5); --Queijo

INSERT INTO ingrediente_lanche values (4, 4); --Ovo
INSERT INTO ingrediente_lanche values (4, 3); --Hambúrguer
INSERT INTO ingrediente_lanche values (4, 5); --Queijo
INSERT INTO ingrediente_lanche values (4, 2); --Bacon

--X-Bacon sem adicionais
INSERT INTO lanche_pedido values (nextval('seq_lanche_pedido'), 1);

--X-Burguer com alface - LIGHT
INSERT INTO lanche_pedido values (nextval('seq_lanche_pedido'), 2);
INSERT INTO ingrediente_lanche_pedido values (2, 1);

--X-Egg com 3 hamburgueres - MUITA CARNE
INSERT INTO lanche_pedido values (nextval('seq_lanche_pedido'), 3);
INSERT INTO ingrediente_lanche_pedido values (currval('seq_lanche_pedido'), 3);
INSERT INTO ingrediente_lanche_pedido values (currval('seq_lanche_pedido'), 3);

--X-Egg-Bacon com 4 queijos - MUITO QUEIJO
INSERT INTO lanche_pedido values (nextval('seq_lanche_pedido'), 4);
INSERT INTO ingrediente_lanche_pedido values (currval('seq_lanche_pedido'), 5);
INSERT INTO ingrediente_lanche_pedido values (currval('seq_lanche_pedido'), 5);
INSERT INTO ingrediente_lanche_pedido values (currval('seq_lanche_pedido'), 5);

--X-Egg-Bacon com 5 queijos - MUITO QUEIJO
INSERT INTO lanche_pedido values (nextval('seq_lanche_pedido'), 4);
INSERT INTO ingrediente_lanche_pedido values (currval('seq_lanche_pedido'), 5);
INSERT INTO ingrediente_lanche_pedido values (currval('seq_lanche_pedido'), 5);
INSERT INTO ingrediente_lanche_pedido values (currval('seq_lanche_pedido'), 5);
INSERT INTO ingrediente_lanche_pedido values (currval('seq_lanche_pedido'), 5);
INSERT INTO ingrediente_lanche_pedido values (currval('seq_lanche_pedido'), 5);