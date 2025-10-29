# Reiseplanlegger

En webapplikasjon som lar brukeren søke etter kollektivreiser i Norge.  
Frontend er laget i **HTML, CSS og JavaScript**, og backend er implementert i **Java (Javalin)** med integrasjon mot **Entur GraphQL API** for rutetider.

---

## 🎨 Brukergrensesnitt

Brukeren møtes av en enkel og moderne forside med bakgrunnsbilde og et sentrert søkeskjema:

- **Valg av billettype:** En dropdown for Tur/Retur eller En vei.
- **Valg av reisende:** En dropdown for Voksen, Barn, etc.
- **Fra / Til:** To inputfelt med autoutfylling (autocomplete) koblet til Entur sitt geocoder-API.
- **Dato og tid:** Dato- og tidsvelgere for avreisetidspunkt.
- **Fortsett-knapp:** Sender inn søket og henter ruter fra backend.

---

## Eksempel av det vi har fått til så langt

| 🏁 Før utfylling         | Tomt skjema med placeholder-tekst “Hvor reiser du fra?” og “Hvor reiser du til?” |
|--------------------------|----------------------------------------------------------------------------------|
| ✍️ Bruker skriver inn “Oslo s” | Dynamisk liste med forslag som “Oslo S, Oslo”, “Oslo lufthavn, Ullensaker” osv. vises |
| ⛳ Bruker velger “Ski, Nordre Follo” | Autocomplete fungerer også for destinasjon |
| 📅 Bruker velger dato/tid | Feltene fylles automatisk i formatet dd.mm.åååå og hh:mm |
| ✅ Trykk på “Fortsett”     | En reise vises med:<br>- reisetid<br>- avgang/ankomst<br>- linjenavn (f.eks. VYG:Line:R22)<br>- transporttype |

---

![Skjermbilde 1](https://github.com/user-attachments/assets/b1bd93be-8fb5-4b93-871c-2a5d9f2d1ef8)
![Skjermbilde 2](https://github.com/user-attachments/assets/ca9c1e39-5bfd-4d5b-bdd9-e8b8d5330584)
![Skjermbilde 3](https://github.com/user-attachments/assets/22dbf70e-ab01-42e7-854e-82d02a88b0c4)
![Skjermbilde 4](https://github.com/user-attachments/assets/bf8d4df1-32cd-4127-bc72-ceeb9d4d6dea)
![Skjermbilde 5](https://github.com/user-attachments/assets/cca52f19-a247-4185-a7c8-90df7e31ce56)

---

## 🧱 Teknologier brukt

- **Frontend:** HTML, CSS, JavaScript  
- **Backend:** Java + Javalin framework  
- **API:** Entur GraphQL Trip & Geocoder API  
- **Byggverktøy:** Maven  
- **Database (kommende):** MySQL  
