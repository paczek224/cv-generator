# CV AI Generator

An AI-powered CV generator that creates professional, tailored resumes from structured user input. The application uses a large language model to generate content optimized for a specific job offer, renders it in a fully customizable visual template, and allows the user to print/export it as a PDF.

---

## Features

- **AI-generated CV content** — fills in a professional summary, rewrites duties and skill descriptions, and adds a GDPR consent footer, all matched to the target job offer
- **4 visual templates** — Classic, Executive, Timeline, Lumina — each works with any of 6 color themes and 6 font families
- **Live preview** — the CV renders in the browser immediately after generation; theme and template can be switched without regenerating
- **PDF export** — prints via the browser's native print dialog, producing a vector PDF with selectable text sized to one continuous page
- **Structured input** — work history, education, certifications, languages, skills (with CEFR / 1–5 levels), social links, and a profile photo
- **Multilingual output** — supports Polish, English, German, French, Spanish
- **GDPR footer** — automatically appended in the output language

---

## Tech Stack

| Layer | Technology |
|---|---|
| Runtime | Java 25 |
| Framework | Spring Boot 4.0.6 |
| AI integration | LangChain4j 1.14.1-beta24 (OpenAI-compatible) |
| LLM | Google Gemini `gemini-3.1-flash-lite` via OpenAI-compatible API |
| Boilerplate reduction | Lombok |
| Frontend | Vanilla HTML / CSS / JavaScript (no build step) |
| PDF rendering | Browser `window.print()` |

---

## Project Structure

```
cv-generator/
├── src/
│   └── main/
│       ├── java/com/example/cvaigenerator/
│       │   ├── CvAiGeneratorApplication.java     # Spring Boot entry point
│       │   ├── controller/
│       │   │   └── CvController.java             # POST /api/cv/generate
│       │   ├── service/
│       │   │   └── CvGeneratorAiClient.java      # @AiService interface — LangChain4j prompt
│       │   └── dto/
│       │       ├── CvRequest.java                # Incoming form data (with nested entries)
│       │       ├── CvResponse.java               # AI-generated structured CV
│       │       ├── PersonalInfo.java
│       │       ├── JobInfo.java
│       │       ├── Education.java
│       │       ├── Certification.java
│       │       ├── Language.java
│       │       ├── SocialMediaLink.java
│       │       ├── Skill.java
│       │       └── SkillLevel.java               # Enum: BEGINNER … MASTER
│       └── resources/
│           ├── application.yaml
│           └── static/
│               ├── index.html                    # Single-page UI (form + live preview)
│               ├── css/styles.css                # All styles (UI + 4 CV templates)
│               └── js/app.js                     # All client-side logic
├── pom.xml
└── README.md
```

---

## How It Works

```
User fills the form
        │
        ▼
POST /api/cv/generate
        │
        ▼
CvController.buildUserData()   ← formats form data into a plain-text prompt
        │
        ▼
CvGeneratorAiClient            ← @AiService + @SystemMessage + @UserMessage
        │   (LangChain4j calls Gemini and maps response to CvResponse)
        ▼
CvResponse (structured JSON)
        │
        ▼
Browser renders CV             ← JS template (Classic / Executive / Timeline / Lumina)
        │
        ▼
window.print()                 ← vector PDF, one continuous page
```

---

## API

### `POST /api/cv/generate`

Accepts a `CvRequest` JSON body and returns a `CvResponse` JSON.

**Request body (abridged):**

```json
{
  "firstName": "Jan",
  "lastName": "Kowalski",
  "position": "Senior QA Engineer",
  "address": "Warsaw, Poland",
  "phone": "+48 000 000 000",
  "email": "jan@example.com",
  "socialLinks": [{ "name": "LinkedIn", "url": "linkedin.com/in/jan" }],
  "workHistory": [{
    "title": "QA Lead",
    "companyName": "Acme Corp",
    "from": "2020-01",
    "currentJob": true,
    "duties": "Test automation, CI/CD"
  }],
  "educationList": [{ "school": "AGH – Computer Science", "from": "2015-10", "to": "2019-06" }],
  "skills": ["Java EXPERT", "Selenium MASTER", "Docker ADVANCED"],
  "certifications": [{ "name": "ISTQB FL", "issuer": "SJSI", "date": "2019-12" }],
  "languages": [{ "name": "Polish", "level": "Native" }, { "name": "English", "level": "C1" }],
  "jobOffer": "Senior Test Automation Engineer at FinTech startup",
  "enhanceCv": true,
  "language": "eng"
}
```

**Skill levels** — either a number `1`–`5` or an enum name appended to the skill string:

| Value | Meaning |
|---|---|
| `BEGINNER` / `1` | Beginner |
| `INTERMEDIATE` / `2` | Intermediate |
| `ADVANCED` / `3` | Advanced |
| `EXPERT` / `4` | Expert |
| `MASTER` / `5` | Master |

---

## Configuration

Configuration is in `src/main/resources/application.yaml`.

The API key **must** be supplied via the `GEMINI_API_KEY` environment variable:

```yaml
langchain4j:
  open-ai:
    chat-model:
      api-key: ${GEMINI_API_KEY}
      model-name: gemini-3.1-flash-lite
      base-url: https://generativelanguage.googleapis.com/v1beta/openai/
```

---

## Getting Started

### Prerequisites

- Java 25+
- Maven 3.9+
- A Gemini API key — obtain one at [Google AI Studio](https://aistudio.google.com)

### Run

```bash
export GEMINI_API_KEY=your_key_here          # Linux / macOS
$env:GEMINI_API_KEY = "your_key_here"        # Windows PowerShell

./mvnw spring-boot:run
```

The application starts on `http://localhost:8080`.

### Build fat JAR

```bash
./mvnw clean package
java -jar target/cv-generator-0.0.1-SNAPSHOT.jar
```

---

## Dependencies

| Dependency | Version | Purpose |
|---|---|---|
| `spring-boot-starter-parent` | 4.0.6 | Spring Boot BOM and plugin management |
| `spring-boot-starter-web` | managed | Embedded Tomcat, REST controllers, static file serving |
| `langchain4j-spring-boot4-starter` | 1.14.1-beta24 | LangChain4j core auto-configuration for Spring Boot 4 |
| `langchain4j-open-ai-spring-boot4-starter` | 1.14.1-beta24 | OpenAI-compatible chat model bean (used for Gemini) |
| `lombok` | managed | `@Getter`, `@ToString`, `@Data` — eliminates boilerplate |
| `openhtmltopdf-pdfbox` | 1.0.10 | HTML → PDF conversion (server-side, reserved for future use) |
| `jsoup` | 1.22.2 | HTML parsing / sanitisation (used alongside openhtmltopdf) |
| `spring-boot-starter-test` | managed | JUnit 5, Mockito, Spring test context |
