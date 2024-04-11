INSERT INTO mountainbikes (id, name, wheel_size, frame_size, gears, amount, price_per_day_part, for_adult,full_suspension, available)
VALUES (1001, 'Hardtail S', ' 29 inch', 'small', 10, 10, 30, true, false, true),
       (1002, 'Hardtail M', ' 29 inch', 'medium', 10, 10, 30, true, false, true),
       (1003, 'Hardtail L', ' 29 inch', 'large', 10, 10, 30, true, false, true),
       (1004, 'Hardtail XL', ' 29 inch', 'extra-large', 10, 10, 30, true, false, true),
       (1005, 'Full Suspension S', ' 29 inch', 'small', 12, 5, 35, true, true, true),
       (1006, 'Full Suspension M', ' 29 inch', 'medium', 12, 5, 35, true, true, true),
       (1007, 'Full Suspension L', ' 29 inch', 'large', 12, 5, 35, true, true, true),
       (1008, 'Full Suspension XL', ' 29 inch', 'extra-large', 12, 5, 35, true, true, true),
       (1009, 'Kids 20 inch', ' 20 inch', 'small', 9, 5, 35, false, true, true),
       (1010, 'Kids 24 inch', ' 24 inch', 'large', 10, 5, 35, false, true, true);

INSERT INTO routes (id, name, route_type, difficulty, starting_point, place, province, route_information, distance, available)
VALUES (1001, 'MTB-route Ede', 'ADULT', 'MODERATE','De Fietser, Akulaan 2, 6717 XN in Ede', 'Ede', 'Gelderland',
        'De route in Ede is een combinatie van uitdagende singletracks met ieder hun eigen charme. De route bevat technisch bochtenwerk, snelle delen met goed lopende kombochten en pumptrack-onderdelen.
Er is goed gebruik gemaakt van de hoogteverschillen in de verschillende gebieden wat zorgt voor een heerlijke flow. De route wordt afgewisseld met verbindingsstukken die helpen om even op adem te komen.
Het is een uitdagend parcours voor zowel beginners als gevorderden.
De route is eenvoudig te combineren met diverse routes in de buurt.', 36.1 , true),

    (1002,'Kinder MTB-route Ede', 'CHILD', 'EASY', 'Hoek Singel en Steakhouse Amadeus, Wekeromseweg 1, 6718SC Ede', 'Ede', 'Gelderland', 'Deze mountainbikeroute is speciaal aangelegd voor kinderen. Natuurlijk mogen ook volwassenen gebruik maken van het rondje maar deze dienen voorrang te geven aan de jongere berijders van de route.
Onderweg kom je stoere bulten, gave kuilen en spannende kombochten tegen. Ook kent de route kleine hoogteverschillen. Kinderen kunnen zo mountainbiken op een leuke en veilige manier. Door de geringe lengte is het mogelijk één of meerdere rondjes te rijden en steeds weer bij hetzelfde startpunt uit te komen.
De route in Ede heeft een speciale bewegwijzering voor kinderen. De markering is te herkennen aan houten palen met in het groen het teken voor de kindermountainbikeroute: dennenboom met daaronder 2 rondjes.
De route is een onderdeel van de mountainbikeroutes Ede (rood) en Lunteren (paars) en dus eenvoudig te combineren. Vanaf het startpunt kun je een verbindingsroute volgen naar Ede (rood/groene bordjes).', 4.6, true)


