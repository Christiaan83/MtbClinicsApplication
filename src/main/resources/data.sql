
INSERT INTO pictures(file_name) values ('KinderRoute_Ede.jpg'),('KinderRoute_Rheden.jpg'),('MTB_Route_Almere.jpg'),('MTB_Route_Amerongen.jpg'),('MTB_Route_Deventer.jpg'),('MTB_route_Ede.jpg'),('MTB_Route_Otterlo.jpg'),
('Full_L.jpg'), ('Full_M.jpg'), ('Full_S.jpg'), ('Full_XL.jpg'),('MTB_S.jpg'),('MTB_M.jpg'),('MTB_L.jpg'),('MTB_XL.jpg'),('Kid20.jpg'),('Kid24.jpg'), ('clinic-beginners.jpg'),('clinic-gevorderd.jpg'),('clinic-gezin.jpg'),('clinic-private.jpg');

INSERT INTO mountainbikes (id, name, wheel_size, frame_size, picture_file_name, gears, amount, price_per_day_part, for_adult,full_suspension, available)
VALUES (1001, 'Hardtail S', ' 29 inch', 'small','MTB_S.jpg',  10, 10, 30, true, false, true),
       (1002, 'Hardtail M', ' 29 inch', 'medium','MTB_M.jpg',  10, 10, 30, true, false, true),
       (1003, 'Hardtail L', ' 29 inch', 'large','MTB_L.jpg', 10, 10, 30, true, false, true),
       (1004, 'Hardtail XL', ' 29 inch', 'extra-large','MTB_XL.jpg', 10, 10, 30, true, false, true),
       (1005, 'Full Suspension S', ' 29 inch', 'small','Full_S.jpg', 12, 5, 35, true, true, true),
       (1006, 'Full Suspension M', ' 29 inch', 'medium','Full_M.jpg', 12, 5, 35, true, true, true),
       (1007, 'Full Suspension L', ' 29 inch', 'large','Full_L.jpg', 12, 5, 35, true, true, true),
       (1008, 'Full Suspension XL', ' 29 inch', 'extra-large', 'Full_XL.jpg', 12, 5, 35, true, true, true),
       (1009, 'Kids 20 inch', ' 20 inch', 'small', 'Kid20.jpg', 9, 5, 25, false, null, true),
       (1010, 'Kids 24 inch', ' 24 inch', 'large', 'Kid24.jpg', 10, 5, 25, false, false, true);

INSERT INTO routes (id, name, route_type, difficulty, starting_point, place, province, picture_file_name, route_information, distance, available)
VALUES (1001, 'MTB-route Ede', 'ADULT', 'MODERATE','De Fietser, Akulaan 2, 6717 XN in Ede.', 'Ede', 'Gelderland', 'MTB_route_Ede.jpg',
        'De route in Ede is een combinatie van uitdagende singletracks met ieder hun eigen charme. De route bevat technisch bochtenwerk, snelle delen met goed lopende kombochten en pumptrack-onderdelen.
Er is goed gebruik gemaakt van de hoogteverschillen in de verschillende gebieden wat zorgt voor een heerlijke flow. De route wordt afgewisseld met verbindingsstukken die helpen om even op adem te komen.
Het is een uitdagend parcours voor zowel beginners als gevorderden.
De route is eenvoudig te combineren met diverse routes in de buurt.', 36.1 , true),

    (1002,'Kinder MTB-route Ede', 'CHILD', 'EASY', 'Hoek Singel en Steakhouse Amadeus, Wekeromseweg 1, 6718SC Ede.', 'Ede', 'Gelderland','KinderRoute_Ede.jpg', 'Deze mountainbikeroute is speciaal aangelegd voor kinderen. Natuurlijk mogen ook volwassenen gebruik maken van het rondje maar deze dienen voorrang te geven aan de jongere berijders van de route.
Onderweg kom je stoere bulten, gave kuilen en spannende kombochten tegen. Ook kent de route kleine hoogteverschillen. Kinderen kunnen zo mountainbiken op een leuke en veilige manier. Door de geringe lengte is het mogelijk één of meerdere rondjes te rijden en steeds weer bij hetzelfde startpunt uit te komen.
De route in Ede heeft een speciale bewegwijzering voor kinderen. De markering is te herkennen aan houten palen met in het groen het teken voor de kindermountainbikeroute: dennenboom met daaronder 2 rondjes.
De route is een onderdeel van de mountainbikeroutes Ede (rood) en Lunteren (paars) en dus eenvoudig te combineren. Vanaf het startpunt kun je een verbindingsroute volgen naar Ede (rood/groene bordjes).', 4.6, true),
    (1003, 'MTB-Route Otterlo', 'ADULT', 'DIFFICULT','Otterlo Events, Mosselsepad 28, 6731 SM in Otterlo.', 'Otterlo', 'Gelderland', 'MTB_Route_Otterlo.jpg','De start van de route is gelegen nabij Otterlo Events. Na wat snelle singletracks kom je aan de achterkant van ’s Heeren Loo uit. Je gaat door het hek en volgt de route over het park. Voorbij de hoofdingang ga je links richting het inmiddels beruchte stukje van MTB Zuid-Veluwe AREA 51. Deze sectie, die wordt gedeeld met de MTBroute Lunteren, kenmerkt zich door steile klimmetjes en snelle afdalingen. Aan het einde van deze pittige track ga je rechtdoor over de brug om route Otterlo te blijven volgen.
Na de brug volgt een deel met hoge kombochten en verrassende hoogteverschillen. Aansluitend steekt de route de Koeweg over. Let hier goed op voor loslopende grazers. Vervolgens steek je de Koeweg weer over en gaat het rechtsaf richting Otterlo. Vlak voor het wildrooster steek je de Koeweg opnieuw over (let op: dit is een druk fietspad) en rijd je over een lange singletrack. Na het bruggetje loopt de singletrack verder langs de paardenrenbaan. Daarna wordt de route terug gelust over een breed zandpad wat deels over een camping loopt. Je eindigt bij het startpunt waar een warme kop koffie en de inmiddels beroemde MTB-koek voor je klaarligt.', 13.3, true ),
    (1004, 'Kinder MTB-route Rheden', 'CHILD', 'MODERATE','Bosque, Harderwijkerweg 28, 6961 GE in Eerbeek','Rheden', 'Gelderland','KinderRoute_Rheden.jpg' ,'De Kinderroute van Rheden is onderdeel van de mountainbikeroute Rheden, die ook wel bekend staat onder de naam Veluwezoom. Dit deel van de route is speciaal aangelegd voor kinderen, is bijna 4 kilometer lang en zit vol technische uitdagingen waar zowel kinderen als volwassenen hun vaardigheden kunnen oefenen. De bochten zijn zo aangelegd dat ze goed te nemen zijn met een kleinere fiets. De lus is te herkennen aan de bordjes met daarop OERRR. Dat is het kinderprogramma van Natuurmonumenten. ', 3.3, true),
    (1005, 'MTB-Route Amerongen', 'ADULT','DIFFICULT', 'Parkeerplaats Bergweg 10 in Amerongen.','Amerongen', 'Utrecht', 'MTB_Route_Amerongen.jpg','De route Amerongen ligt in het deel dat van oorsprong 2 landgoederen waren: het Amerongse bos en het Zuilensteinse bos. De route loopt afwisselend over vlakke en heuvelachtige delen van de Utrechtse Heuvelrug. De route is een afwisselende route met veel singletracks, brede lange klimmen en leuke uitdagende afdalingen.
Deze route kan gecombineerd worden met de routes in Rhenen, Kwintelooijen en Leersum.', 18.4, true ),
    (1006, 'MTB-Route Deventer', 'ADULT', 'EASY', 'Start route Oostermaet, 33 kilometer: Kanaaldijk West, 7433 PS in Schalkhaar', 'Deventer', 'Overijssel','MTB_Route_Deventer.jpg' , 'De mountainbikeroute Deventer bestaat uit drie lussen. De korte lus (Wechelerveld) heeft een lengte van 4 kilometer.
Deze lus bevat uitdagende bochtjes, zandbultjes, boomstronken en andere natuurlijke obstakels.
Na deze korte lus kan de route vervolgd worden door een 30 kilometer (Oostermaet) lange lus. Deze lus voert je verder over Spijkvoorde, landgoed De Bannink, de Kiekenbelt, het Oostermaat en de Zandbelt. De route loopt via zandwegen en oude enken door het oudste bosgebied van deze omgeving en bevat uitdagende stukken singletrack. Lus 3 (Oostermaet 2.2) is 16 kilometer.', 32.8, true),
    (1007, 'MTB-Route Almere', 'ADULT', 'MODERATE', 'Trailcenter Outdoorpark SEC Almere, Verlaatweg 2, 1358 AJ in Almere', 'Almere', 'Flevoland', 'MTB_Route_Almere.jpg','De mountainbikeroute over de voormalige stortplaats Braambergen loopt over drie heuvels. Daarom zijn er op een relatief klein oppervlak verhoudingsgewijs veel hoogtemeters te maken. De route kan als een echt klimparkoers worden gekarakteriseerd.  De trails bestaan voor 99% uit singletrack die voorzien zijn van een asfaltgranulaat-verharding. De drie heuvels op het voormalige stortterrein hebben allemaal hun eigen karakter.
De eerste heuvel, de Bosberg, is meteen al een pittige start. De route over deze eerste heuvel biedt veel variatiemogelijkheden. In de aanloop zitten een paar korte, pittige klimmetjes en kun je kiezen om de hoofdroute te rijden of kun je kiezen voor een jumpline, voorafgegaan door een paar kombochten, een skinny, een korte pumptrack en een rock garden. Kies je de hoofdroute dan kun je alsnog twee varianten rijden. Via een shortcut kun je een verkorte route rijden die uiteindelijk 5,7 kilometer lang is, of je pakt de hele heuvel. Je krijgt dan een mengeling van flowy uphill- en downhill-trails voor je wielen, met als klapstuk een paar zeer steile en korte beklimmingen waar je veel power en techniek nodig hebt, gevolgd door een afdaling met twee hoge kombochten waar je op topsnelheid doorheen kunt.
De tweede heuvel loopt op de flanken van de Wijnberg waarbij je langs een veld met zonnepanelen komt én langs een wijngaard! Je rijdt hier geen seconde vlak en moet voortdurend klimmen en dalen. Er zit een lekkere flow in deze trails.
De derde heuvel (de Schapenberg) is het klapstuk van de route. Hier overwin je op een afstand van 2,4 kilometer ruim 100 hoogtemeters waarbij je ook nog eens verschillende lastige bruggetjes moet passeren.

De toegang tot het terrein is geregeld via een hek met een toegangscode. Die code is te vinden op het routevignet dat verplicht en noodzakelijk is om toegang te krijgen tot de trails op Braambergen.
De route Braambergen is met een korte verbindingsroute verbonden met de mountainbikeroute Almere-Kemphaan.', 7.0, false );

INSERT INTO trainings ( id,name, difficulty, location, picture_file_name, description, price, start_time, end_time, training_in_group)
VALUES
(1001, 'Mountainbike clinic beginner', 'EASY', 'De Fietser, Akulaan 2, 6717 XN Ede','clinic-beginners.jpg', 'In deze clinic ligt de nadruk op de basis van het mountainbiken. Je leert meer controle over de mountainbike en meer vertrouwen te krijgen. Er is veel aandacht voor balans en de juiste houding. De ideale clinic voor de beginnende mountainbiker of de gevorderde mountainbiker die er zeker van wil zijn dat de basis perfect is.', 67.50, '10:00:00', '12:00:00', true),

(1002, 'Mountainbike clinic gevorderd','MODERATE', 'De Fietser, Akulaan 2, 6717 XN Ede','clinic-gevorderd.jpg', 'In deze clinic leer je de controle te behouden wanneer de situatie uitdagender wordt. We breiden de basis uit en gaan dieper in op de diverse technieken zoals balans, houding, de mountainbike het werk laten doen, klimmen en dalen met obstakels. De ideale clinic voor de gevorderde mountainbiker die de puntjes op de i wil zetten.',67.50, '10:00:00', '12:00:00', true),

(1003,'Ouder - kind clinic', 'EASY','De Fietser, Akulaan 2, 6717 XN Ede', 'clinic-gezin.jpg', 'Mountainbiken met je zoon of dochter, hoe gaaf is dat? Het wordt nog leuker wanneer je beide beter leert mountainbiken. Deze mountainbike clinic is speciaal voor vader, moeder, opa of oma met (klein)zoon of (klein)dochter.' ||
                                                                         '
Het niveau en zwaarte wordt aangepast op de kids, zodat dit voor iedereen goed haalbaar zal zijn.', 49.50,'13:30:00', '15:30:00', true ),
(1004, 'Privé clinic','MODERATE', 'De Fietser, Akulaan 2, 6717 XN Ede', 'clinic-private.jpg','Heb je liever alle aandacht of wil je een specifiek onderwerp behandelen? Dan is een privéclinic een goede keuze.

Tijdens een privéclinic kunnen we ieder thema behandelen van jouw keuze. Wij bekijken wat je al kunt en wat je nog wilt leren. Aan de hand daarvan vullen we de clinic in. Je kunt alleen of met 2 personen deelnemen.', 250.00,'14:00:00', '16:30:00', false );

INSERT INTO unregistered_users(id, first_name, last_name, email, mobile_number) VALUES (1001, 'Christiaan', 'Zielman', 'c.zielman@mtbclinics-ede.com', 0650425689);

INSERT INTO registered_users (username, password, first_name, last_name, email, mobile_number, is_active )
VALUES('admin','$2a$12$U6cIRWm1ps9KZ/1//6RisuQ/q.KngzrykxENEfsgnTUcTRjTx/WPC', 'Christiaan', 'Zielman', 'christiaan@mtb-ede.nl', '0650412077', true),
      ('piet2004','$2a$12$K5DKU9rnazAvpDEwbLnO9uIiSMN5dJGk9BS/jVwLwE2NJ4EBsq89K', 'Piet', 'de Groot', 'piet@gmail.com', '0649872065', true );

INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN'), ('admin', 'ROLE_USER'), ('piet2004', 'ROLE_USER');

INSERT INTO rentals (id, start_date, start_time, end_time, renting_whole_day)
VALUES (1001,'2024-05-11','10:00:00', '16:30:00', true ),
       (1002,'2024-05-14','13:00:00', '17:00:00', false );

INSERT INTO mountainbike_id(id, mountainbike_id)
VALUES (1001,1002), (1002, 1001);

INSERT INTO unregistered_user_id(id, unregistered_user_id)
VALUES (1001,1001), (1002, 1001);

INSERT INTO bookings (id, booking_date, message, training_id, registered_user_id)
VALUES (1001, '2024-06-23', '98070', 1003, 'admin'),
       (1002, '2024-06-24', '98072', 1002, 'piet2004');

INSERT INTO contact_forms (id, first_name, last_name, email, mobile_number, message)
VALUES (1001, 'Jan', 'den Bosch', 'jan@email.com', 0679564679, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.' ),
       (1002, 'Petra', 'de Haan', 'dehaanpetra@email.nl', 0612345678, 'At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae.' );

