package uz.pdp.quiz_first_app.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.quiz_first_app.entity.Category;
import uz.pdp.quiz_first_app.entity.Option;
import uz.pdp.quiz_first_app.entity.Question;
import uz.pdp.quiz_first_app.repo.CategoryRepo;
import uz.pdp.quiz_first_app.repo.OptionRepo;
import uz.pdp.quiz_first_app.repo.QuestionRepo;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataInitializationService {

    private final CategoryRepo categoryRepo;
    private final QuestionRepo questionRepo;
    private final OptionRepo optionRepo;

    public void initializeData() {
        Category sport = Category.builder().name("Sport").build();
        Category cinema = Category.builder().name("Cinema").build();
        Category history = Category.builder().name("History").build();
        Category music = Category.builder().name("Music").build();

        categoryRepo.save(sport);
        categoryRepo.save(cinema);
        categoryRepo.save(history);
        categoryRepo.save(music);

        saveSportQuestions(sport);
        saveCinemaQuestions(cinema);
        saveHistoryQuestions(history);
        saveMusicQuestions(music);
    }

    private void saveSportQuestions(Category sport) {
        List<Question> sportQuestions = Arrays.asList(
                Question.builder()
                        .category(sport)
                        .textEn("Who won the FIFA World Cup in 2018?")
                        .textRu("Кто выиграл чемпионат мира по футболу в 2018 году?")
                        .textUz("2018 yilda FIFA Jahon chempionatida kim g'olib bo'ldi?")
                        .build(),
                Question.builder()
                        .category(sport)
                        .textEn("Which country hosted the 2016 Summer Olympics?")
                        .textRu("Какая страна принимала летние Олимпийские игры 2016 года?")
                        .textUz("2016 yilgi yozgi Olimpiya o'yinlarini qaysi mamlakat mezbonlik qildi?")
                        .build(),
                Question.builder()
                        .category(sport)
                        .textEn("In which sport is the Stanley Cup awarded?")
                        .textRu("В каком виде спорта вручается Кубок Стэнли?")
                        .textUz("Qaysi sport turida Stenli kubogi topshiriladi?")
                        .build(),
                Question.builder()
                        .category(sport)
                        .textEn("Who is the fastest man in the world?")
                        .textRu("Кто самый быстрый человек в мире?")
                        .textUz("Dunyodagi eng tezkor odam kim?")
                        .build(),
                Question.builder()
                        .category(sport)
                        .textEn("Which sport is known as 'the beautiful game'?")
                        .textRu("Какой вид спорта известен как 'красивая игра'?")
                        .textUz("'Go'zal o'yin' deb qaysi sport turi tanilgan?")
                        .build(),
                Question.builder()
                        .category(sport)
                        .textEn("Who holds the record for the most home runs in a single MLB season?")
                        .textRu("Кому принадлежит рекорд по количеству хоум-ранов за один сезон MLB?")
                        .textUz("MLB mavsumida eng ko'p xomeran uchun rekord kimga tegishli?")
                        .build(),
                Question.builder()
                        .category(sport)
                        .textEn("Which country has won the most Olympic gold medals?")
                        .textRu("Какая страна завоевала больше всего олимпийских золотых медалей?")
                        .textUz("Qaysi mamlakat eng ko'p Olimpiya oltin medallarini qo'lga kiritgan?")
                        .build(),
                Question.builder()
                        .category(sport)
                        .textEn("Who is known as the 'King of Football'?")
                        .textRu("Кто известен как 'король футбола'?")
                        .textUz("Futbolning 'qiroli' kim?")
                        .build(),
                Question.builder()
                        .category(sport)
                        .textEn("Which sport uses the term 'alley-oop'?")
                        .textRu("В каком виде спорта используется термин 'аллей-уп'?")
                        .textUz("Qaysi sport turida 'alley-oop' atamasi qo'llaniladi?")
                        .build(),
                Question.builder()
                        .category(sport)
                        .textEn("What is the maximum score in a single game of ten-pin bowling?")
                        .textRu("Какой максимальный счет в одной игре в десятикегельный боулинг?")
                        .textUz("O'n pinli bouling o'yinida maksimal ball qancha?")
                        .build()
        );

        questionRepo.saveAll(sportQuestions);

        List<Option> sportOptions = Arrays.asList(
                Option.builder().isCorrect(true).textEn("France").textRu("Франция").textUz("Frantsiya").question(sportQuestions.get(0)).build(),
                Option.builder().textEn("Croatia").textRu("Хорватия").textUz("Xorvatiya").question(sportQuestions.get(0)).build(),
                Option.builder().textEn("Brazil").textRu("Бразилия").textUz("Braziliya").question(sportQuestions.get(0)).build(),

                Option.builder().isCorrect(true).textEn("Brazil").textRu("Бразилия").textUz("Braziliya").question(sportQuestions.get(1)).build(),
                Option.builder().textEn("China").textRu("Китай").textUz("Xitoy").question(sportQuestions.get(1)).build(),
                Option.builder().textEn("Russia").textRu("Россия").textUz("Rossiya").question(sportQuestions.get(1)).build(),

                Option.builder().isCorrect(true).textEn("Ice Hockey").textRu("Хоккей").textUz("Xokkey").question(sportQuestions.get(2)).build(),
                Option.builder().textEn("Basketball").textRu("Баскетбол").textUz("Basketbol").question(sportQuestions.get(2)).build(),
                Option.builder().textEn("Baseball").textRu("Бейсбол").textUz("Beysbol").question(sportQuestions.get(2)).build(),

                Option.builder().isCorrect(true).textEn("Usain Bolt").textRu("Усэйн Болт").textUz("Useyn Bolt").question(sportQuestions.get(3)).build(),
                Option.builder().textEn("Mo Farah").textRu("Мо Фара").textUz("Mo Farah").question(sportQuestions.get(3)).build(),
                Option.builder().textEn("Tyson Gay").textRu("Тайсон Гэй").textUz("Tayson Gay").question(sportQuestions.get(3)).build(),

                Option.builder().isCorrect(true).textEn("Soccer").textRu("Футбол").textUz("Futbol").question(sportQuestions.get(4)).build(),
                Option.builder().textEn("Tennis").textRu("Теннис").textUz("Tennis").question(sportQuestions.get(4)).build(),
                Option.builder().textEn("Basketball").textRu("Баскетбол").textUz("Basketbol").question(sportQuestions.get(4)).build(),

                Option.builder().isCorrect(true).textEn("Barry Bonds").textRu("Барри Бондс").textUz("Barri Bonds").question(sportQuestions.get(5)).build(),
                Option.builder().textEn("Babe Ruth").textRu("Бэйб Рут").textUz("Beyb Rut").question(sportQuestions.get(5)).build(),
                Option.builder().textEn("Mark McGwire").textRu("Марк Макгвайр").textUz("Mark Makgvayer").question(sportQuestions.get(5)).build(),

                Option.builder().isCorrect(true).textEn("USA").textRu("США").textUz("AQSH").question(sportQuestions.get(6)).build(),
                Option.builder().textEn("Russia").textRu("Россия").textUz("Rossiya").question(sportQuestions.get(6)).build(),
                Option.builder().textEn("China").textRu("Китай").textUz("Xitoy").question(sportQuestions.get(6)).build(),

                Option.builder().isCorrect(true).textEn("Pelé").textRu("Пеле").textUz("Pele").question(sportQuestions.get(7)).build(),
                Option.builder().textEn("Maradona").textRu("Марадона").textUz("Maradona").question(sportQuestions.get(7)).build(),
                Option.builder().textEn("Messi").textRu("Месси").textUz("Messi").question(sportQuestions.get(7)).build(),

                Option.builder().isCorrect(true).textEn("Basketball").textRu("Баскетбол").textUz("Basketbol").question(sportQuestions.get(8)).build(),
                Option.builder().textEn("Volleyball").textRu("Волейбол").textUz("Voleybol").question(sportQuestions.get(8)).build(),
                Option.builder().textEn("Tennis").textRu("Теннис").textUz("Tennis").question(sportQuestions.get(8)).build(),

                Option.builder().isCorrect(true).textEn("300").textRu("300").textUz("300").question(sportQuestions.get(9)).build(),
                Option.builder().textEn("200").textRu("200").textUz("200").question(sportQuestions.get(9)).build(),
                Option.builder().textEn("400").textRu("400").textUz("400").question(sportQuestions.get(9)).build()
        );
        optionRepo.saveAll(sportOptions);
    }

    private void saveCinemaQuestions(Category cinema) {
        List<Question> cinemaQuestions = Arrays.asList(
                Question.builder()
                        .category(cinema)
                        .textEn("Who directed the movie 'Inception'?")
                        .textRu("Кто режиссер фильма 'Начало'?")
                        .textUz("'Inception' filmini kim suratga olgan?")
                        .build(),
                Question.builder()
                        .category(cinema)
                        .textEn("Which movie won the Academy Award for Best Picture in 2020?")
                        .textRu("Какой фильм получил премию Оскар за лучший фильм в 2020 году?")
                        .textUz("2020 yilda eng yaxshi film uchun Akademiya mukofotini qaysi film qo'lga kiritdi?")
                        .build(),
                Question.builder()
                        .category(cinema)
                        .textEn("Which actor played the character of Jack Dawson in 'Titanic'?")
                        .textRu("Какой актер сыграл роль Джека Доусона в 'Титанике'?")
                        .textUz("'Titanik' filmida Jek Douson rolini qaysi aktyor ijro etgan?")
                        .build(),
                Question.builder()
                        .category(cinema)
                        .textEn("Which movie features the quote, 'Here's looking at you, kid'?")
                        .textRu("В каком фильме звучит фраза 'Here's looking at you, kid'?")
                        .textUz("Qaysi filmda 'Here's looking at you, kid' iborasi ishlatilgan?")
                        .build(),
                Question.builder()
                        .category(cinema)
                        .textEn("Who is the first woman to win an Academy Award for Best Director?")
                        .textRu("Кто первая женщина, получившая Оскар за лучшую режиссуру?")
                        .textUz("Eng yaxshi rejissyor uchun Akademiya mukofotini qo'lga kiritgan birinchi ayol kim?")
                        .build(),
                Question.builder()
                        .category(cinema)
                        .textEn("Which film franchise features a character named Frodo Baggins?")
                        .textRu("Какая серия фильмов включает персонажа по имени Фродо Бэггинс?")
                        .textUz("Qaysi film seriyasida Frodo Baggins nomli personaj ishtirok etadi?")
                        .build(),
                Question.builder()
                        .category(cinema)
                        .textEn("Who composed the score for 'Star Wars'?")
                        .textRu("Кто написал музыку к 'Звездным войнам'?")
                        .textUz("'Star Wars' uchun musiqa kim tomonidan yozilgan?")
                        .build(),
                Question.builder()
                        .category(cinema)
                        .textEn("Which actress played the character of Katniss Everdeen in 'The Hunger Games'?")
                        .textRu("Какая актриса сыграла роль Китнисс Эвердин в 'Голодных играх'?")
                        .textUz("'The Hunger Games' filmida Katniss Everdeen rolini qaysi aktrisa ijro etgan?")
                        .build(),
                Question.builder()
                        .category(cinema)
                        .textEn("What is the highest-grossing film of all time?")
                        .textRu("Какой фильм имеет наибольшие кассовые сборы за все время?")
                        .textUz("Eng yuqori daromad keltirgan film qaysi?")
                        .build(),
                Question.builder()
                        .category(cinema)
                        .textEn("Which movie is based on the novel 'To Kill a Mockingbird'?")
                        .textRu("Какой фильм основан на романе 'Убить пересмешника'?")
                        .textUz("Qaysi film 'To Kill a Mockingbird' romani asosida suratga olingan?")
                        .build()
        );

        questionRepo.saveAll(cinemaQuestions);

        List<Option> cinemaOptions = Arrays.asList(
                Option.builder().isCorrect(true).textEn("Christopher Nolan").textRu("Кристофер Нолан").textUz("Krishtofyer Nolan").question(cinemaQuestions.get(0)).build(),
                Option.builder().textEn("Steven Spielberg").textRu("Стивен Спилберг").textUz("Stiven Spilberg").question(cinemaQuestions.get(0)).build(),
                Option.builder().textEn("James Cameron").textRu("Джеймс Кэмерон").textUz("Jeyms Kameron").question(cinemaQuestions.get(0)).build(),

                Option.builder().isCorrect(true).textEn("Parasite").textRu("Паразиты").textUz("Parazitlar").question(cinemaQuestions.get(1)).build(),
                Option.builder().textEn("1917").textRu("1917").textUz("1917").question(cinemaQuestions.get(1)).build(),
                Option.builder().textEn("Joker").textRu("Джокер").textUz("Joker").question(cinemaQuestions.get(1)).build(),

                Option.builder().isCorrect(true).textEn("Leonardo DiCaprio").textRu("Леонардо ДиКаприо").textUz("Leonardo Di Kaprio").question(cinemaQuestions.get(2)).build(),
                Option.builder().textEn("Brad Pitt").textRu("Брэд Питт").textUz("Bred Pitt").question(cinemaQuestions.get(2)).build(),
                Option.builder().textEn("Matt Damon").textRu("Мэтт Дэймон").textUz("Mett Deymon").question(cinemaQuestions.get(2)).build(),

                Option.builder().isCorrect(true).textEn("Casablanca").textRu("Касабланка").textUz("Kasablanka").question(cinemaQuestions.get(3)).build(),
                Option.builder().textEn("Gone with the Wind").textRu("Унесенные ветром").textUz("Shamol bilan ketganlar").question(cinemaQuestions.get(3)).build(),
                Option.builder().textEn("Citizen Kane").textRu("Гражданин Кейн").textUz("Fuqarolik Keyn").question(cinemaQuestions.get(3)).build(),

                Option.builder().isCorrect(true).textEn("Kathryn Bigelow").textRu("Кэтрин Бигелоу").textUz("Ketrin Bigelou").question(cinemaQuestions.get(4)).build(),
                Option.builder().textEn("Sofia Coppola").textRu("София Коппола").textUz("Sofiya Koppola").question(cinemaQuestions.get(4)).build(),
                Option.builder().textEn("Greta Gerwig").textRu("Грета Гервиг").textUz("Greta Gervig").question(cinemaQuestions.get(4)).build(),

                Option.builder().isCorrect(true).textEn("The Lord of the Rings").textRu("Властелин колец").textUz("Uzuklar hokimi").question(cinemaQuestions.get(5)).build(),
                Option.builder().textEn("Harry Potter").textRu("Гарри Поттер").textUz("Garri Potter").question(cinemaQuestions.get(5)).build(),
                Option.builder().textEn("The Chronicles of Narnia").textRu("Хроники Нарнии").textUz("Narniya Xronikalari").question(cinemaQuestions.get(5)).build(),

                Option.builder().isCorrect(true).textEn("John Williams").textRu("Джон Уильямс").textUz("Jon Uilyams").question(cinemaQuestions.get(6)).build(),
                Option.builder().textEn("Hans Zimmer").textRu("Ханс Циммер").textUz("Xans Zimmer").question(cinemaQuestions.get(6)).build(),
                Option.builder().textEn("James Horner").textRu("Джеймс Хорнер").textUz("Jeyms Xorner").question(cinemaQuestions.get(6)).build(),

                Option.builder().isCorrect(true).textEn("Jennifer Lawrence").textRu("Дженнифер Лоуренс").textUz("Jennifer Lourens").question(cinemaQuestions.get(7)).build(),
                Option.builder().textEn("Emma Watson").textRu("Эмма Уотсон").textUz("Emma Uotson").question(cinemaQuestions.get(7)).build(),
                Option.builder().textEn("Shailene Woodley").textRu("Шейлин Вудли").textUz("Sheylin Vudli").question(cinemaQuestions.get(7)).build(),

                Option.builder().isCorrect(true).textEn("Avatar").textRu("Аватар").textUz("Avatar").question(cinemaQuestions.get(8)).build(),
                Option.builder().textEn("Avengers: Endgame").textRu("Мстители: Финал").textUz("Qasoskorlar: Endgame").question(cinemaQuestions.get(8)).build(),
                Option.builder().textEn("Titanic").textRu("Титаник").textUz("Titanik").question(cinemaQuestions.get(8)).build(),

                Option.builder().isCorrect(true).textEn("To Kill a Mockingbird").textRu("Убить пересмешника").textUz("Chumchuqni o'ldirish").question(cinemaQuestions.get(9)).build(),
                Option.builder().textEn("Pride and Prejudice").textRu("Гордость и предубеждение").textUz("Mag'rurlik va xurofot").question(cinemaQuestions.get(9)).build(),
                Option.builder().textEn("Moby Dick").textRu("Моби Дик").textUz("Mobi Dik").question(cinemaQuestions.get(9)).build()
        );
        optionRepo.saveAll(cinemaOptions);
    }

    private void saveHistoryQuestions(Category history) {
        List<Question> historyQuestions = Arrays.asList(
                Question.builder()
                        .category(history)
                        .textEn("Who was the first President of the United States?")
                        .textRu("Кто был первым президентом США?")
                        .textUz("AQShning birinchi prezidenti kim edi?")
                        .build(),
                Question.builder()
                        .category(history)
                        .textEn("In which year did the Titanic sink?")
                        .textRu("В каком году затонул Титаник?")
                        .textUz("Titanik qaysi yili cho'kdi?")
                        .build(),
                Question.builder()
                        .category(history)
                        .textEn("Who discovered America?")
                        .textRu("Кто открыл Америку?")
                        .textUz("Amerikani kim kashf etdi?")
                        .build(),
                Question.builder()
                        .category(history)
                        .textEn("Which war was fought between the North and South regions in the United States?")
                        .textRu("Какая война велась между северными и южными регионами в США?")
                        .textUz("AQShda shimoliy va janubiy mintaqalar o'rtasida qaysi urush bo'lgan?")
                        .build(),
                Question.builder()
                        .category(history)
                        .textEn("Who was the British Prime Minister during World War II?")
                        .textRu("Кто был премьер-министром Великобритании во время Второй мировой войны?")
                        .textUz("Ikkinchi jahon urushi davrida Buyuk Britaniyaning bosh vaziri kim edi?")
                        .build(),
                Question.builder()
                        .category(history)
                        .textEn("What year did the Berlin Wall fall?")
                        .textRu("В каком году пал Берлинский мур?")
                        .textUz("Berlin devori qaysi yili qulagan?")
                        .build(),
                Question.builder()
                        .category(history)
                        .textEn("Which ancient civilization built the Pyramids of Giza?")
                        .textRu("Какая древняя цивилизация построила пирамиды Гизы?")
                        .textUz("Qaysi qadimgi tsivilizatsiya Giza piramidalari qurgan?")
                        .build(),
                Question.builder()
                        .category(history)
                        .textEn("Who was the first man to step on the Moon?")
                        .textRu("Кто был первым человеком, ступившим на Луну?")
                        .textUz("Oydan qadam qo'ygan birinchi odam kim edi?")
                        .build(),
                Question.builder()
                        .category(history)
                        .textEn("What was the main cause of World War I?")
                        .textRu("Какова была основная причина Первой мировой войны?")
                        .textUz("Birinchi jahon urushining asosiy sababi nima edi?")
                        .build(),
                Question.builder()
                        .category(history)
                        .textEn("Who wrote the 'I Have a Dream' speech?")
                        .textRu("Кто написал речь 'У меня есть мечта'?")
                        .textUz("'Mening orzuim bor' nutqini kim yozgan?")
                        .build()
        );

        questionRepo.saveAll(historyQuestions);

        List<Option> historyOptions = Arrays.asList(
                Option.builder().isCorrect(true).textEn("George Washington").textRu("Джордж Вашингтон").textUz("Jorj Vashington").question(historyQuestions.get(0)).build(),
                Option.builder().textEn("Thomas Jefferson").textRu("Томас Джефферсон").textUz("Tomas Jefferson").question(historyQuestions.get(0)).build(),
                Option.builder().textEn("Abraham Lincoln").textRu("Авраам Линкольн").textUz("Avraam Linkoln").question(historyQuestions.get(0)).build(),

                Option.builder().isCorrect(true).textEn("1912").textRu("1912").textUz("1912").question(historyQuestions.get(1)).build(),
                Option.builder().textEn("1910").textRu("1910").textUz("1910").question(historyQuestions.get(1)).build(),
                Option.builder().textEn("1914").textRu("1914").textUz("1914").question(historyQuestions.get(1)).build(),

                Option.builder().isCorrect(true).textEn("Christopher Columbus").textRu("Кристофер Колумб").textUz("Kristofor Kolumb").question(historyQuestions.get(2)).build(),
                Option.builder().textEn("Leif Erikson").textRu("Лейф Эриксон").textUz("Leyf Erikson").question(historyQuestions.get(2)).build(),
                Option.builder().textEn("Amerigo Vespucci").textRu("Америго Веспуччи").textUz("Amerigo Vespuchchi").question(historyQuestions.get(2)).build(),

                Option.builder().isCorrect(true).textEn("The Civil War").textRu("Гражданская война").textUz("Fuqarolar urushi").question(historyQuestions.get(3)).build(),
                Option.builder().textEn("The Revolutionary War").textRu("Революционная война").textUz("Inqilobiy urush").question(historyQuestions.get(3)).build(),
                Option.builder().textEn("The War of 1812").textRu("Война 1812 года").textUz("1812 yil urushi").question(historyQuestions.get(3)).build(),

                Option.builder().isCorrect(true).textEn("Winston Churchill").textRu("Уинстон Черчилль").textUz("Uinston Cherchill").question(historyQuestions.get(4)).build(),
                Option.builder().textEn("Neville Chamberlain").textRu("Невилл Чемберлен").textUz("Nevill Chemberlen").question(historyQuestions.get(4)).build(),
                Option.builder().textEn("Clement Attlee").textRu("Клемент Эттли").textUz("Klement Ettli").question(historyQuestions.get(4)).build(),

                Option.builder().isCorrect(true).textEn("1989").textRu("1989").textUz("1989").question(historyQuestions.get(5)).build(),
                Option.builder().textEn("1991").textRu("1991").textUz("1991").question(historyQuestions.get(5)).build(),
                Option.builder().textEn("1987").textRu("1987").textUz("1987").question(historyQuestions.get(5)).build(),

                Option.builder().isCorrect(true).textEn("The Ancient Egyptians").textRu("Древние египтяне").textUz("Qadimgi misrliklar").question(historyQuestions.get(6)).build(),
                Option.builder().textEn("The Mayans").textRu("Майя").textUz("Mayyalar").question(historyQuestions.get(6)).build(),
                Option.builder().textEn("The Aztecs").textRu("Ацтеки").textUz("Asteklar").question(historyQuestions.get(6)).build(),

                Option.builder().isCorrect(true).textEn("Neil Armstrong").textRu("Нил Армстронг").textUz("Nil Armstrong").question(historyQuestions.get(7)).build(),
                Option.builder().textEn("Buzz Aldrin").textRu("Базз Олдрин").textUz("Bazz Oldrin").question(historyQuestions.get(7)).build(),
                Option.builder().textEn("Yuri Gagarin").textRu("Юрий Гагарин").textUz("Yuriy Gagarin").question(historyQuestions.get(7)).build(),

                Option.builder().isCorrect(true).textEn("The assassination of Archduke Franz Ferdinand").textRu("Убийство эрцгерцога Франца Фердинанда").textUz("Arxduke Frants Ferdinandning o'ldirilishi").question(historyQuestions.get(8)).build(),
                Option.builder().textEn("The sinking of the Lusitania").textRu("Потопление Лузитании").textUz("Lusitaniyaning cho'kishi").question(historyQuestions.get(8)).build(),
                Option.builder().textEn("The invasion of Belgium").textRu("Вторжение в Бельгию").textUz("Belgiya istilosi").question(historyQuestions.get(8)).build(),

                Option.builder().isCorrect(true).textEn("Martin Luther King Jr.").textRu("Мартин Лютер Кинг мл.").textUz("Martin Lyuter King Jr.").question(historyQuestions.get(9)).build(),
                Option.builder().textEn("Malcolm X").textRu("Малькольм Икс").textUz("Malkolm X").question(historyQuestions.get(9)).build(),
                Option.builder().textEn("John F. Kennedy").textRu("Джон Ф. Кеннеди").textUz("Jon F. Kennedi").question(historyQuestions.get(9)).build()
        );

        optionRepo.saveAll(historyOptions);
    }

    private void saveMusicQuestions(Category music) {
        List<Question> musicQuestions = Arrays.asList(
                Question.builder()
                        .category(music)
                        .textEn("Who is known as the King of Pop?")
                        .textRu("Кто известен как Король поп-музыки?")
                        .textUz("Pop musiqa qiroli kim?")
                        .build(),
                Question.builder()
                        .category(music)
                        .textEn("Which band released the album 'Abbey Road'?")
                        .textRu("Какая группа выпустила альбом 'Abbey Road'?")
                        .textUz("Qaysi guruh 'Abbey Road' albomini chiqardi?")
                        .build(),
                Question.builder()
                        .category(music)
                        .textEn("Who composed the Four Seasons?")
                        .textRu("Кто сочинил 'Времена года'?")
                        .textUz("'To'rt faslni' kim bastalagan?")
                        .build(),
                Question.builder()
                        .category(music)
                        .textEn("Which singer is known as the 'Queen of Soul'?")
                        .textRu("Какая певица известна как 'Королева соула'?")
                        .textUz("'Ruh malikasi' deb qaysi qo'shiqchi tanilgan?")
                        .build(),
                Question.builder()
                        .category(music)
                        .textEn("What is the best-selling album of all time?")
                        .textRu("Какой альбом является самым продаваемым за всю историю?")
                        .textUz("Eng ko'p sotilgan albom qaysi?")
                        .build(),
                Question.builder()
                        .category(music)
                        .textEn("Which artist released the hit song 'Shape of You'?")
                        .textRu("Какой артист выпустил хит 'Shape of You'?")
                        .textUz("'Shape of You' xit qo'shig'ini qaysi ijodkor chiqargan?")
                        .build(),
                Question.builder()
                        .category(music)
                        .textEn("Who was the lead singer of Queen?")
                        .textRu("Кто был солистом группы Queen?")
                        .textUz("Queen guruhining bosh qo'shiqchisi kim edi?")
                        .build(),
                Question.builder()
                        .category(music)
                        .textEn("Which classical composer was deaf?")
                        .textRu("Какой классический композитор был глухим?")
                        .textUz("Qaysi klassik kompozitor kar bo'lgan?")
                        .build(),
                Question.builder()
                        .category(music)
                        .textEn("Who is the best-selling female artist of all time?")
                        .textRu("Кто является самой продаваемой женщиной-исполнителем всех времен?")
                        .textUz("Barcha davrlarning eng ko'p sotilgan ayol ijodkori kim?")
                        .build(),
                Question.builder()
                        .category(music)
                        .textEn("Which band is known for the song 'Bohemian Rhapsody'?")
                        .textRu("Какая группа известна песней 'Bohemian Rhapsody'?")
                        .textUz("'Bohemian Rhapsody' qo'shig'i bilan tanilgan guruh qaysi?")
                        .build()
        );

        questionRepo.saveAll(musicQuestions);

        List<Option> musicOptions = Arrays.asList(
                Option.builder().isCorrect(true).textEn("Michael Jackson").textRu("Майкл Джексон").textUz("Maykl Jekson").question(musicQuestions.get(0)).build(),
                Option.builder().textEn("Elvis Presley").textRu("Элвис Пресли").textUz("Elvis Presli").question(musicQuestions.get(0)).build(),
                Option.builder().textEn("Prince").textRu("Принс").textUz("Prins").question(musicQuestions.get(0)).build(),

                Option.builder().isCorrect(true).textEn("The Beatles").textRu("Битлз").textUz("The Beatles").question(musicQuestions.get(1)).build(),
                Option.builder().textEn("The Rolling Stones").textRu("Роллинг Стоунз").textUz("The Rolling Stones").question(musicQuestions.get(1)).build(),
                Option.builder().textEn("Pink Floyd").textRu("Пинк Флойд").textUz("Pink Floyd").question(musicQuestions.get(1)).build(),

                Option.builder().isCorrect(true).textEn("Antonio Vivaldi").textRu("Антонио Вивальди").textUz("Antonio Vivaldi").question(musicQuestions.get(2)).build(),
                Option.builder().textEn("Johann Sebastian Bach").textRu("Иоганн Себастьян Бах").textUz("Iogann Sebastyan Bax").question(musicQuestions.get(2)).build(),
                Option.builder().textEn("Ludwig van Beethoven").textRu("Людвиг ван Бетховен").textUz("Ludvig van Betxoven").question(musicQuestions.get(2)).build(),

                Option.builder().isCorrect(true).textEn("Aretha Franklin").textRu("Арета Франклин").textUz("Areta Franklin").question(musicQuestions.get(3)).build(),
                Option.builder().textEn("Whitney Houston").textRu("Уитни Хьюстон").textUz("Uitni Hyuston").question(musicQuestions.get(3)).build(),
                Option.builder().textEn("Beyoncé").textRu("Бейонсе").textUz("Beyonce").question(musicQuestions.get(3)).build(),

                Option.builder().isCorrect(true).textEn("Thriller").textRu("Триллер").textUz("Thriller").question(musicQuestions.get(4)).build(),
                Option.builder().textEn("Back in Black").textRu("Назад в черное").textUz("Back in Black").question(musicQuestions.get(4)).build(),
                Option.builder().textEn("The Dark Side of the Moon").textRu("Темная сторона луны").textUz("The Dark Side of the Moon").question(musicQuestions.get(4)).build(),

                Option.builder().isCorrect(true).textEn("Ed Sheeran").textRu("Эд Ширан").textUz("Ed Shiran").question(musicQuestions.get(5)).build(),
                Option.builder().textEn("Justin Bieber").textRu("Джастин Бибер").textUz("Jastin Biber").question(musicQuestions.get(5)).build(),
                Option.builder().textEn("Bruno Mars").textRu("Бруно Марс").textUz("Bruno Mars").question(musicQuestions.get(5)).build(),

                Option.builder().isCorrect(true).textEn("Freddie Mercury").textRu("Фредди Меркьюри").textUz("Freddi Merkuri").question(musicQuestions.get(6)).build(),
                Option.builder().textEn("Brian May").textRu("Брайан Мэй").textUz("Brayan Mey").question(musicQuestions.get(6)).build(),
                Option.builder().textEn("Roger Taylor").textRu("Роджер Тейлор").textUz("Rodjer Teylor").question(musicQuestions.get(6)).build(),

                Option.builder().isCorrect(true).textEn("Ludwig van Beethoven").textRu("Людвиг ван Бетховен").textUz("Ludvig van Betxoven").question(musicQuestions.get(7)).build(),
                Option.builder().textEn("Johann Sebastian Bach").textRu("Иоганн Себастьян Бах").textUz("Iogann Sebastyan Bax").question(musicQuestions.get(7)).build(),
                Option.builder().textEn("Wolfgang Amadeus Mozart").textRu("Вольфганг Амадей Моцарт").textUz("Volg'ang Amadey Motsart").question(musicQuestions.get(7)).build(),

                Option.builder().isCorrect(true).textEn("Madonna").textRu("Мадонна").textUz("Madonna").question(musicQuestions.get(8)).build(),
                Option.builder().textEn("Whitney Houston").textRu("Уитни Хьюстон").textUz("Uitni Hyuston").question(musicQuestions.get(8)).build(),
                Option.builder().textEn("Mariah Carey").textRu("Мэрайя Кэри").textUz("Meraya Keri").question(musicQuestions.get(8)).build(),

                Option.builder().isCorrect(true).textEn("Queen").textRu("Куин").textUz("Queen").question(musicQuestions.get(9)).build(),
                Option.builder().textEn("The Beatles").textRu("Битлз").textUz("The Beatles").question(musicQuestions.get(9)).build(),
                Option.builder().textEn("The Rolling Stones").textRu("Роллинг Стоунз").textUz("The Rolling Stones").question(musicQuestions.get(9)).build()
        );

        optionRepo.saveAll(musicOptions);
    }

}