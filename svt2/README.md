# Serverske veb tehnologije - Vežbe 2


## Inverzija kontrole

- _Inversion of Control_ (IoC) je tehnika kojom se povezivanje objekata vrši u
toku izvršavanja (runtime) a ta veza nije poznata u toku kompaliranja
(compile time)
- Da bi kontejner mogao da izvrši povezivanje, objekti moraju imati
odgovarajuće uputstvo
- Jedna forma IoC je _dependency injection_ (DI)

Spring kontejner je osnova Spring radnog okvira. Spring kontejner koristi dependency injection (DI) da upravlja
komponentama koje čine aplikaciju.

Kontejner će kreirati objekte, uvezati ih, konfigurisati i upravljati njihovim životnim ciklusom od kreiranja do uništenja.
Kontejner na osnovu konfiguracije koja može biti iskazana XML fajlovima, Java anotacijama ili Java kodom zna šta da radi.

### Spring beans

Objekti koji formiraju aplikaciju, a koji se nalaze u nadležnosti Spring kontejnera zovu se zrna (_beans_).
Klasa postaje bean dodavanjem konfiguracije u vidu Java anotacija ili
XML metapodataka koje su potrebne Spring kontejneru da bi znao:

- Kako da kreira bean
- Detalje o životnom ciklusu beana
- Zavisnosti koje bean poseduje

Podrazumevano ponašanje beana je _singleton_ kada se uvek kreira samo
jedna instanca i ona se injektuje kada je tražena. Ako to nije željeno ponašanje, opseg se može eksplicitno definisati.

| Svojstvo      | Opis |
| ----------- | ----------- |
| singleton      | Jedna instanca po Spring kontejneru (podrazumevani opseg)       |
| prototype   | Nova instanca se kreira svaki put kada se objekat injektuje ili kada se traži iz konteksta aplikacije |
| request   | Nova instanca za svaki HTTP zahtev     |
| session   | Nova instanca za svaku HTTP  sesiju       |

Od novijih Spring verzija postoji mogućnost lakše i čistije konfiguracije korišćenjem anotacija umesto XML fajlova.

- Anotacije se dodaju na postojeći Java kod.
- Anotiranjem Java klase anotacijom `@Configuration` ta klasa postaje
konfiguraciona klasa iz koje Spring kontejner čita uputstva.
- `@Bean` anotacija govori Springu da u konfiguracionoj klasi metoda koja je
anotirana tom anotacijom treba da bude registrovana kao bean.

Konkretne klase mogu biti anotirane posebnim
anotacijama i tako registrovane kao komponente umesto
korišćenja `@Bean` anotacije u Java konfiguracionoj klasi.

- `@Component` anotacija:
  - Stereotip za generalnu upotrebu (sve u Spring kontejneru je bean tj. komponenta).
- `@Controller` anotacija:
  - Označava da je klasa Spring MVC kontroler.
- `@Repository` anotacija:
  - Označava da je klasa repozitorijum podataka.
- `@Service` anotacija:
  - Označava da klasa sadrži neku poslovnu logiku.

### Tipovi dependency injection-a

- Kroz konstruktor
  - DI se ostvaruje kada kontejner pozove konstruktor beana koji kao paramentre ima zavisne komponente.
- Kroz set metodu
  - DI se ostvaruje kada kontejner pozove set metodu beana posle pozivanja konstruktora bez parametara.
- Kroz atribut
  - Ostvarivo isključivo pomoću anotacija za razliku od preostalih načina.

- `@Autowired`, `@Inject`, `@Resource` – rade DI povezivanje i mogu se primeniti
na konstruktor, set metode ili atribute.
- `@Autowired` je Spring specifična i najčešće korišćena anotacija za DI.
- `@Qualifier` - u kombinaciji sa `@Autowired` može se koristiti da jednoznačno
naglasi koja komponenta se injektuje (ukoliko ima više komponenti koje
služe istoj svrsi).

## Aspekti

Primer Spring aplikacije `aspect-example` u kojoj je definisan jedan aspekt (__TimeLoggingAspect__).

Da bi se definisao aspekt, potrebno je Java klasu anotirati `@Aspect` anotacijom. Zatim svaku metodu ove klase anotirati anotacijom koja će opisati u kom trenutku će se aspekt izvršiti, a kao atribut ove anotacije potrebno je navesti __pointcut__ izraz kojim se definiše konkretno mesto u aplikaciji na kojem će aspekt biti primenjen. Anotacije kojim se anotiraju metode su sledeće:

- `@Before`: pre poziva metode na koju se aspekt odnosi
- `@After`: nakon metode (bez obzira na ishod metode)
- `@AfterReturning`: nakon uspešnog završetka metode
- `@AfterThrowing` : nakon što metoda izazove izuzetak
- `@Around`: omotač oko metode, tako što se deo koda izvršava pre, a deo posle metode.

__Pointcut__ izrazom se definiše __šablon__, što znači da se aspekt primenjuje na __svaku__ metodu koja se uklapa u definisani šablon!

## Više informacija

- https://www.journaldev.com/2583/spring-aop-example-tutorial-aspect-advice-pointcut-joinpoint-annotations
- https://docs.spring.io/spring/docs/2.0.x/reference/aop.html
- https://www.eclipse.org/aspectj/doc/released/progguide/language.html