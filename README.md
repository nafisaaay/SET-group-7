# Reiseplanlegger

En webapplikasjon som lar brukeren søke etter kollektivreiser i Norge.  
Frontend er laget i **HTML, CSS og JavaScript**, og backend er implementert i **Java (Javalin)** med integrasjon mot **Entur GraphQL API** for rutetider.

---

## 🎨 Brukergrensesnitt

Brukeren møtes av en enkel og moderne forside med et illustrert bakgrunnsbilde og et sentrert søkeskjema:

- **Valg av billettype:** En dropdown for Én vei eller Tur/Retur.
- **Valg av reisende:** En dropdown for Student, Voksen, Barn, etc.
- **Fra / Til:** To input-felt med autoutfylling (autocomplete) koblet til Entur sitt geocoder-API.
- **Dato og tid:** Dato- og tidsvelgere for avreisetidspunkt.
- **Fortsett-knapp:** Sender inn søket og henter ruter fra backend.

---

## Eksempel på brukerflyt

| 🏁 Før utfylling              | Tomt skjema med placeholder-tekst “Hvor reiser du fra?” og “Hvor reiser du til?” og dropdowns for billettype og reisende. |
|------------------------------|------------------------------------------------------------------------------------------------------|
| ✍️ Bruker skriver inn “Oslo” | Dynamisk liste med forslag som “Oslo lufthavn, Ullensaker”, “Oslo S, Oslo”, “Oslo bussterminal, Oslo” vises under inputfeltet. *(Se bilde 1)* |
| ⛳ Bruker fyller ut “Til”     | Autocomplete fungerer også for destinasjon, f.eks. “Remmen”, og forslag som “Remmen Høgskolen, Halden” vises. *(Se bilde 2)* |
| 📅 Bruker velger dato/tid     | Feltene fylles automatisk i formatet dd.mm.åååå og hh:mm. *(Se bilde 3)* |
| ✅ Trykk på “Fortsett”        | En reiserute vises med:<br>- total varighet<br>- avgang/ankomst for hvert ledd<br>- linjenavn (f.eks. VYG:Line:RE20)<br>- transporttype (tog, buss)<br>- evt. gå-avstand | 
|                              | *(Se bilde 5)* |

---

**Skjermbilder:**

1. ![Skjermbilde 1](https://github.com/user-attachments/assets/b1bd93be-8fb5-4b93-871c-2a5d9f2d1ef8)
2. ![Skjermbilde 2](https://github.com/user-attachments/assets/ca9c1e39-5bfd-4d5b-bdd9-e8b8d5330584)
3. ![Skjermbilde 3](https://github.com/user-attachments/assets/22dbf70e-ab01-42e7-854e-82d02a88b0c4)
4. ![Skjermbilde 4](https://github.com/user-attachments/assets/bf8d4df1-32cd-4127-bc72-ceeb9d4d6dea)
5. <img width="2447" height="1337" alt="Skjermbilde 2025-11-02 093250" src="https://github.com/user-attachments/assets/3c846c67-19e2-42fa-b573-f9f045d4a01d" />
6. <img width="2456" height="1339" alt="Skjermbilde 2025-11-02 103708" src="https://github.com/user-attachments/assets/013ba3bf-fb52-426b-9e6e-9527b874bdf9" />
7. <img width="2457" height="1338" alt="Skjermbilde 2025-11-02 103727" src="https://github.com/user-attachments/assets/82e2a03f-754b-4106-8b7a-a6d37b806bbe" />
8. <img width="2457" height="1340" alt="Skjermbilde 2025-11-02 103739" src="https://github.com/user-attachments/assets/6b3b2c86-bcdb-4474-8184-841f3b0408a3" />
9. <img width="2454" height="1332" alt="Skjermbilde 2025-11-02 103800" src="https://github.com/user-attachments/assets/b8435423-b050-4748-a985-ae5e5cfe71ed" />







---

## 🧱 Teknologier brukt

- **Frontend:** HTML, CSS, JavaScript  
- **Backend:** Java + Javalin framework  
- **API:** Entur GraphQL Trip & Geocoder API  
- **Byggverktøy:** Maven  
- **Database (kommende):** MySQL  
