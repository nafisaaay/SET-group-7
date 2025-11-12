# Reiseplanlegger

En webapplikasjon som lar brukeren søke etter kollektivruter i Norge, og blir presentert den raskeste og mest effektive ruten.
Frontend er laget i **HTML, CSS og JavaScript**, og backend er implementert i **Java (Javalin)** med integrasjon mot **Entur GraphQL API** for rutetider.

---

## 🎨 Brukergrensesnitt

Brukeren møtes av en enkel og moderne forside med et illustrert bakgrunnsbilde og et sentrert søkeskjema:

- **Valg av billettype:** En dropdown for Én vei eller Tur/Retur.
- **Valg av reisende:** En dropdown for Student, Voksen, Barn eller honnør.
- **Fra / Til:** To input-felt med autoutfylling (autocomplete) koblet til Entur sitt geocoder-API.
- **Dato og tid:** Dato- og tidsvelgere for avreisetidspunkt.
- **Fortsett-knapp:** Sender inn søket og henter ruter fra backend.
- Brukeren får presentert ruten deres, samt et kart som viser dem gangavstanden deres 

---

## Eksempel på brukerflyt

| 🏁 Før utfylling              | Tomt skjema med placeholder-tekst “Hvor reiser du fra?” og “Hvor reiser du til?” og dropdowns for billettype og reisende. |
|------------------------------|------------------------------------------------------------------------------------------------------|
| ✍️ Bruker skriver inn “Oslo” | Dynamisk liste med forslag som “Oslo lufthavn, Ullensaker”, “Oslo S, Oslo”, “Oslo bussterminal, Oslo” vises under inputfeltet. *(Se bilde 1)* |
| ⛳ Bruker fyller ut “Til”     | Autocomplete fungerer også for destinasjon, f.eks. “Remmen”, og forslag som “Remmen Høgskolen, Halden” vises. *(Se bilde 2)* |
| 📅 Bruker velger dato/tid     | Feltene fylles automatisk i formatet dd.mm.åååå og hh:mm. *(Se bilde 3)* |
| ✅ Trykk på “Fortsett”        | En reiserute vises med:<br>- total varighet<br>- avgang/ankomst for hvert ledd <br>- totalpris for hele turen <br>- linjenavn (f.eks. VYG:Line:RE20)<br>- transporttype (tog, buss)<br>- evt. gå-avstand | 
|                              | *(Se bilde 5)* |

---

**Skjermbilder:**

Start side
<img width="1505" height="827" alt="G7-webb start" src="https://github.com/user-attachments/assets/80c44f4c-2d43-4abd-a651-83a2fe7313a5" />

Lagt inn søk 
<img width="1186" height="676" alt="G7-webb sok" src="https://github.com/user-attachments/assets/0b3cf00f-a97b-4e07-98d0-2da1a256ede7" />

Søkefeltet blir byttet ut med brukrens valgte rute 
<img width="1469" height="842" alt="G7-webb rute" src="https://github.com/user-attachments/assets/84b90ee9-933d-45ba-a32b-3a5d183c1205" />

Betaling






---

## 🧱 Teknologier brukt

- **Frontend:** HTML, CSS, JavaScript  
- **Backend:** Java + Javalin framework  
- **API:** Entur GraphQL Trip & Geocoder API  
- **Byggverktøy:** Maven  
- **Database (kommende):** MySQL  
