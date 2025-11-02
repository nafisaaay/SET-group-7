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
|                              | *(Se bilde 4 og 5)* |

---

**Skjermbilder:**

1. ![Skjermbilde 1](https://github.com/user-attachments/assets/b1bd93be-8fb5-4b93-871c-2a5d9f2d1ef8)
2. ![Skjermbilde 2](https://github.com/user-attachments/assets/ca9c1e39-5bfd-4d5b-bdd9-e8b8d5330584)
3. ![Skjermbilde 3](https://github.com/user-attachments/assetsHere is how you can add Image 6 to your README.md file:

``/22dbf70e-ab01-42e7-854e-82d02a88b0c4)
4. ![Sk``markdown
**Skjermbilde 5:**
![Skjermbilde 5](httpsjermbilde 4](https://github.com/user-attachments/assets://github.com/user-attachments/assets/cca52f/bf8d4df1-32cd-4127-bc72-ceeb9d4d6dea)
5. ![19-a247-4185-a7c8-90df7e31ce56)

**Skjermbilde 6:**
Skjermbilde 5](https://github.com/user-attachments/assets![Skjermbilde 6](https://github.com/user-attachments/assets//cca52f19-a247-4185-a7c8-90df7e31ce56)

---

##cca52f19-a247-4185-a7c8-90df7e31ce56)
