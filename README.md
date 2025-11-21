# Reiseplanlegger

En webapplikasjon som lar brukeren søke etter kollektivruter i Norge, og blir presentert den raskeste og mest effektive ruten.
Frontend er laget i **HTML, CSS og JavaScript**, og backend er implementert i **Java (Javalin)** med integrasjon mot **Entur GraphQL API** for rutetider.

---

## 🎨 Brukergrensesnitt

Brukeren møtes av en enkel og moderne forside med et illustrert bakgrunnsbilde og et sentrert søkeskjema:

- **Valg av billettype:** En dropdown for Én vei eller Tur/Retur.
- **Valg av reisende:** En dropdown for Student, Voksen, Barn eller honnør.
- **Nåværende posisjon:** En knapp på fra feltet hvor brukeren kan velge sin nåværende posisjon som "fra punkt"
- **Fra / Til:** To input-felt med autoutfylling (autocomplete) koblet til Entur sitt geocoder-API.
- **Dato og tid:** Dato- og tidsvelgere for avreisetidspunkt.
- **Fortsett-knapp:** Sender inn søket og henter ruter fra backend.
- Brukeren får presentert ruten deres, samt et kart som viser dem gangavstanden deres 

---

## Eksempel på brukerflyt

| 🏁 Før utfylling              | Tomt skjema med placeholder-tekst “Hvor reiser du fra?” og “Hvor reiser du til?” og dropdowns for billettype og reisende. |
|------------------------------|------------------------------------------------------------------------------------------------------|
| ✍️ Bruker kan enten skriver inn ønkset startpunkt, eller bruke sin egen posisjon som start. | Dynamisk liste med forslag som “Oslo lufthavn, Ullensaker”, “Oslo S, Oslo”, “Oslo bussterminal, Oslo” vises under inputfeltet. Dersom brukeren ønsker å velge start punkt. Ved valg av posisjon, dukker det opp et godkjenningsfelt slik at brukerens nåværende posisjon kan brukes *(Se bilde 2)*. |
| ⛳ Bruker fyller ut “Til”     | Autocomplete fungerer også for destinasjon, f.eks. “Remmen”, og forslag som “Remmen Høgskolen, Halden” vises. |
| 📅 Bruker velger dato/tid     | Feltene fylles automatisk i formatet dd.mm.åååå og hh:mm. *(Se bilde 3)* |
| ✅ Trykk på “Fortsett”        | En reiserute vises med:<br>- total varighet<br>- avgang/ankomst for hvert ledd <br>- totalpris for hele turen <br>- linjenavn (f.eks. VYG:Line:RE20)<br>- transporttype (tog, buss)<br>- gangavstand  <br>- *(se bilde 4)*
| 💰 Trykk på "gå videre til betaling" | En bilett vises med qr kode slik at billetten kan valideres, og bilde endres daglig *(se bilde 5)*
|🔎 Bruker velger en av 5 byer | Et felt for å velge hvilken by man ønsker å utforske *(se bilde 6)*
|🎯 Brukeren velger mellom de to første kategoriene | En liste av interessepunkter basert på brukerens valgte kategori vises på skjermen *(se bilde 7)*


---

**Skjermbilder:**


Start side
<img width="1506" height="813" alt="hovedside" src="https://github.com/user-attachments/assets/94200844-b7d5-4684-94ee-13e2432e4940" />

Godkjenningsfelt hvis brukeren velger å bruke sin egen posisjon. 
<img width="1512" height="817" alt="posisjon" src="https://github.com/user-attachments/assets/cc69be0a-20cf-4fdc-bb94-754eda555762" />

Ferdig lagt inn søk 
<img width="1143" height="606" alt="lagt inn rute" src="https://github.com/user-attachments/assets/d8be6759-c776-4642-8834-63737f0b0b4a" />

Felt som viser informasjon om ruten og et tilsvarende kart
<img width="1256" height="734" alt="ferdig rute" src="https://github.com/user-attachments/assets/3c6e7342-6cd0-4976-a768-2088f1261960" />

Billett
<img width="1324" height="796" alt="Billett" src="https://github.com/user-attachments/assets/d69c57e3-c3fc-43d7-a131-d4a2c439b17f" />

Valg av by
<img width="1360" height="668" alt="tips til attraksjoner" src="https://github.com/user-attachments/assets/39b32d26-752e-4803-aa10-0a0f230346a7" />

Tips til interessepunkter basert på valgt kategori 
<img width="1237" height="819" alt="valgt kategori" src="https://github.com/user-attachments/assets/a1574756-0d07-4b41-894b-4813187da4a4" />






---

## 🧱 Teknologier brukt

- **Frontend:** HTML, CSS, JavaScript  
- **Backend:** Java + Javalin framework  
- **API:** Entur GraphQL Trip & Geocoder API  
- **Byggverktøy:** Maven  
- **Database :** MySQL  
