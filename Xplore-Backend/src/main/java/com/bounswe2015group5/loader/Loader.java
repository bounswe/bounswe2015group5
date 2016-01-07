package com.bounswe2015group5.loader;

import com.bounswe2015group5.model.*;
import com.bounswe2015group5.repository.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Loader implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private TagRepo tagRepo;
    @Autowired
    private ContributionRepo contributionRepo;
    @Autowired
    private RelationRepo relationRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private RateRepo rateRepo;

    private Logger log = Logger.getLogger(Loader.class);

    //sample data
    private Contribution cont1,
            cont2,
            cont3,
            cont4,
            cont5,
            cont6,
            cont7,
            cont8,
            cont9,
            cont10,
            cont11,
            cont12;

    private User hanefi;
    private User abbas;
    private User hasan;

    private Tag netNeutrality;
    private Tag markZuckerberg;
    private Tag india;
    private Tag airtelZero;
    private Tag eef;
    private Tag fcc;
    private Tag internet;
    private Tag cowMesh;
    private Tag freeBasics,
            federalCourt,
            freedomOfPress,
            censure,
            syria,
            turkishCourt;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadUsers();
        loadTags();
        loadContributions();
        loadRelations();
        loadComments();
        loadRates();
    }

    private void loadTags() {
        netNeutrality = new Tag();
        netNeutrality.setName("Net Neutrality");
        netNeutrality.setConcept("Principle");
        tagRepo.save(netNeutrality);

        markZuckerberg = new Tag();
        markZuckerberg.setName("Mark Zuckerberg");
        markZuckerberg.setConcept("Computer programmer");
        tagRepo.save(markZuckerberg);

        india = new Tag();
        india.setName("India");
        india.setConcept("Country");
        tagRepo.save(india);

        airtelZero = new Tag();
        airtelZero.setName("Airtel Zero");
        airtelZero.setConcept("Telecommunications service company");
        tagRepo.save(airtelZero);

        eef = new Tag();
        eef.setName("Electronic Frontier Foundation");
        eef.setConcept("Non-profit group");
        tagRepo.save(eef);

        fcc = new Tag();
        fcc.setName("Federal Communications Commission");
        fcc.setConcept("US Government Agency");
        tagRepo.save(fcc);

        internet = new Tag();
        internet.setName("Internet");
        internet.setConcept("Global system");
        tagRepo.save(internet);

        freeBasics = new Tag();
        freeBasics.setName("Free Basics");
        freeBasics.setConcept("Partnership");
        tagRepo.save(freeBasics);

        federalCourt = new Tag();
        federalCourt.setName("Federal Court");
        federalCourt.setConcept("Justice system");
        tagRepo.save(federalCourt);

        freedomOfPress = new Tag();
        freedomOfPress.setName("Freedom of Press");
        freedomOfPress.setConcept("Freedom");
        tagRepo.save(freedomOfPress);

        censure = new Tag();
        censure.setName("Censure");
        censure.setConcept("Measure");
        tagRepo.save(censure);

        syria = new Tag();
        syria.setName("Syrian Conflict");
        syria.setConcept("Civil war");
        tagRepo.save(syria);

        turkishCourt = new Tag();
        turkishCourt.setName("Turkish Court");
        turkishCourt.setConcept("Justice");
        tagRepo.save(turkishCourt);
    }

    private void loadContributions() {
        cont1 = new Contribution();
        cont1.setTitle("Facebook’s Rebuttal to Mahesh Murthy on Free Basics, with Replies");
        cont1.setContent("The year 2015 will undoubtedly go down as the time period during which important debates regarding net neutrality, India’s technology industry and the country’s Internet ecosystem were settled. The last year has seen zero-rating, the controversial practice by which mobile network operators refuse to charge for data used by specific Internet applications, become a major source of criticism and discussion, with net neutrality activists pointing out that it reduces competition, distorts the free market and allows major technology companies to play kingmaker in the Indian Internet space.\n" +
                "\n" +
                "One of the more controversial zero-rating initiatives includes Facebook’s Free Basics – a suite of Internet applications that are packaged together by Reliance Communications in India and given  free to the telecom operator’s users. While the exact legal status of Free Basics and other similar initiatives still remains murky, with telecom regulator TRAI in the middle of its regulatory process, Facebook has embarked on a massive advertisement campaign in favour of its Free Basics service. In the last few weeks, the Silicon Valley-based company has conducted polls, lobbied with industry executives and published its arguments in the public sphere.\n" +
                "\n" +
                "Indian venture capitalist Mahesh Murthy recently wrote a piece, which was republished in The Wire last week, that took a critical look at some of Facebook’s claims regarding Free Basics. In the piece below, we publish Facebook’s replies to the original article as well as Murthy’s responses. We believe that the debate between them is emblematic of the larger net neutrality concerns that plague India.");
        cont1.setCreator(hanefi);
        contributionRepo.save(cont1);


        cont2 = new Contribution();
        cont2.setTitle("Net Neutrality: Nasscom announces opposition to Airtel Zero, Free Basics platforms");
        cont2.setContent("The Telecom Regulatory Authority of India (Trai) has invited comments from public till January 7 on its paper on differential data pricing - a key aspect of the raging debate on net neutrality.");
        cont2.setCreator(abbas);
        contributionRepo.save(cont2);

        cont3 = new Contribution();
        cont3.setTitle("Facebook's \"free internet\" programme hits a roadblock in India");
        cont3.setContent("\"WHO could possibly be against this?\" Mark Zuckerberg, Facebook’s boss, asked in an editorial in the Times of India on December 28th. The this in question is Free Basics, a programme that gives its users free access to Facebook and a handful of other online services on their smartphones in 36 poor countries. According to Mr Zuckerberg, Free Basics acts as a gateway drug to the internet: half of those who first experience the internet through the service start paying for full internet access within a month. Though the programme is promoted by Facebook, its costs are borne by the mobile-telecoms operators it works with—in the case of India, Reliance Communications, the country’s fourth-largest.");
        cont3.setCreator(hasan);
        contributionRepo.save(cont3);

        cont4 = new Contribution();
        cont4.setTitle("Facebook's try to convince India");
        cont4.setContent("Mark Zuckerberg posted this story: \"Just look at the story of Ganesh Nimbalkar and his wife Bharati, who support their family of four by farming a five acre plot their family has tended for generations in Maharashtra. Ganesh struggled with traditional farming methods in a region plagued by droughts, but last year he started using Free Basics -- accessing services like AccuWeather, which helped him work better through the monsoon season, and Reuters Market Light, which helped him understand commodity prices and get a better deal for his crops. By using Free Basics, Ganesh has doubled his crop yield, eradicated insect infestations and even invested in new crops and livestock. Today, nearly 1 billion people are currently without internet access in India. Now with Internet.org's Free Basics available to everyone in India, many more people like Ganesh and Bharati will have access to the information and opportunity the internet brings.\"");
        cont4.setCreator(hasan);
        contributionRepo.save(cont4);

        cont5 = new Contribution();
        cont5.setTitle("In India, Fierce Opposition Builds Against Facebook's Free Basics");
        cont5.setContent("An unprecedented resistance is building up in India against Facebook’s Free Basics program as students, startup entrepreneurs and an ever-growing number of internet users join the protest. India’s telecom regulator has already ordered Facebook’s program be put on hold while it takes a final decision on the subject of differential pricing, a move by some telecom providers to provide free internet access through programs such as Free Basics. The critics of Free Basics say the battle is a fight for Net Neutrality, the notion that no one company or authority should be allowed to restrict access and that all users get equal access to all websites. The outcome of the India battle will influence the fate of Facebook Inc founder Mark Zuckerberg’s pet project in dozens of smaller countries around the world where the understanding about Free Basics is feeble and the internet giant is likely to forcefully influence the widespread adoption of the program.");
        cont5.setCreator(hasan);
        contributionRepo.save(cont5);

        cont6 = new Contribution();
        cont6.setTitle("Bandwidth Throttling");
        cont6.setContent("Bandwidth throttling is the intentional slowing of Internet service by an Internet service provider. It is a reactive measure employed in communication networks to regulate network traffic and minimize bandwidth congestion. Bandwidth throttling can occur at different locations on the network. On a local area network (LAN), a sysadmin may employ bandwidth throttling to help limit network congestion and server crashes. On a broader level, the Internet service provider may use bandwidth throttling to help reduce a user's usage of bandwidth that is supplied to the local network. Throttling can be used to actively limit a user's upload and download rates on programs such as video streaming, BitTorrent protocols and other file sharing applications, as well as even out the usage of the total bandwidth supplied across all users on the network. Bandwidth throttling is also often used in Internet applications, in order to spread a load over a wider network to reduce local network congestion, or over a number of servers to avoid overloading individual ones, and so reduce their risk of crashing, and gain additional revenue by compelling users to use more expensive pricing schemes where bandwidth is not throttled.");
        cont6.setCreator(hasan);
        contributionRepo.save(cont6);

        cont7 = new Contribution();
        cont7.setTitle("Mark Zuckerberg invited Narendra Modi to Facebook HQ");
        cont7.setContent("Narendra Modi and CEO both get emotional at event at Facebook headquarters in California, as pair discuss the role of internet and technology in India. The Indian prime minister Narendra Modi seemed at home at Facebook headquarters on Sunday, in a town hall event seen by many as an attempt by Facebook to gain much-needed support for its expansive ambitions in India. Facebook CEO Mark Zuckerberg was certainly in a welcoming mood, changing his profile picture to include an Indian flag and writing: “I changed my profile picture to support Digital India, the Indian government’s effort to connect rural communities to the internet and give people access to more services online.” Given criticism in India of Facebook regarding its alleged promotion of its own applications and services over Indian equivalents, Zuckerberg and the Internet.org team hoped Modi’s visit would attract support.");
        cont7.setCreator(hasan);
        contributionRepo.save(cont7);

        cont8 = new Contribution();
        cont8.setTitle("Internet.org announced");
        cont8.setContent("Mark Zuckerberg: \"A year ago, I announced Internet.org, our effort to bring affordable internet access to everyone in the world.We believe that every person should have access to free basic internet services - tools for health, education, jobs and basic communication. Over the past year we've been working with mobile operators around the world to deliver on this goal. We're starting to see this vision become a reality, and we've already helped 3 million people access the internet who had no access before. Today, I'm excited to announce the launch of the Internet.org app in Zambia. This provides people in Zambia with free data access to basic internet services like the ones I mentioned above, and means Zambia will now be the first country where we've been able to provide a whole set of free basic services. Right now, only 15% of people in Zambia have access to the internet. Soon, everyone will be able to use the internet for free to find jobs, get help with reproductive health and other aspects of health, and use tools like Facebook to stay connected with the people they love. This is a big step forward in achieving the mission of Facebook and Internet.org. We're looking forward to bringing free basic services to more countries soon.\"");
        cont8.setCreator(hasan);
        contributionRepo.save(cont8);

        cont9 = new Contribution();
        cont9.setTitle("FCC Passes Strict Net Neutrality Regulations On 3-2 Vote");
        cont9.setContent("As expected, the Federal Communications Commission (FCC) passed new net neutrality regulations today on a vote of 3-2, with the Commission’s two Democratic appointees joining Chairman Tom Wheeler in voting yes. The Commission’s two Republican-appointed members both voted no.\n" +
                "\n" +
                "Notably, the FCC’s plan is now known to have undergone a last-minute revision to remove a potential weakness in its formation, pointed out by Google, that might have allowed for some paid prioritization. If you were curious about Google’s take on net neutrality, that fact should settle the question.\n" +
                "\n" +
                "The CEO of Etsy, an online marketplace, spoke before the commission voted to applaud the FCC for putting into place bright line rules, and voting to protect the Internet.\n" +
                "\n" +
                "Up first from the commission, Commissioner Mignon Clyburn said in her remarks that the framers of America would be pleased with the FCC’s plan. The commissioner went on to call today’s vote the FCC’s third bite at the apple. Clyburn also disclosed, as was previously reported, that she had helped shape part of the order, and also listed a number of changes she would have preferred to see in the order itself. The commissioner wrapped by arguing that individuals who are worried about rate regulation are worrying unnecessarily.\n" +
                "\n" +
                "Commissioner Jessica Rosenworcel argued that the United States’ Internet economy is the envy of the world. We invented it. The app economy began right here on our shores. She went on to call the Internet our printing press and our town square.");
        cont9.setCreator(hanefi);
        contributionRepo.save(cont9);

        cont10 = new Contribution();
        cont10.setTitle("EFF in Court to Argue NSA Data Collection from Internet Backbone Is Unconstitutional");
        cont10.setContent("The Electronic Frontier Foundation (EFF) will argue on Friday before a federal court that the National Security Agency (NSA) is violating the Fourth Amendment by copying and searching data that it collects by tapping into the Internet backbone. The hearing on a motion for partial summary judgment in Jewel v. NSA will be at 9 am on Dec. 19 before Judge Jeffrey White at the federal courthouse in Oakland.");
        cont10.setCreator(abbas);
        contributionRepo.save(cont10);

        cont11 = new Contribution();
        cont11.setTitle("Cumhuriyet daily’s Dundar, Gul arrested over report on Syria arms transfer");
        cont11.setContent("The editor-in-chief of the Cumhuriyet daily, Can Dundar, and the paper's Ankara representative Erdem Gul have been arrested on charges of being members of a terror organization, espionage and revealing confidential documents -- charges that could see them spend life in prison.");
        cont11.setCreator(abbas);
        contributionRepo.save(cont11);

        cont12 = new Contribution();
        cont12.setTitle("Istanbul court rejects appeal against arrests of Cumhuriyet daily's Can Dündar, Erdem Gül");
        cont12.setContent("The Istanbul 7th Court of Peace said that that there was \"no unlawful aspect\" regarding the decision of their arrest. The court has then transferred the appeal demand to a higher court of the Istanbul 8th Court of Peace for further evaluation.");
        cont12.setCreator(hanefi);
        contributionRepo.save(cont12);
    }

    private void loadRelations() {

        relationRepo.save(new Relation(india, cont1, hanefi));
        relationRepo.save(new Relation(freeBasics, cont1, hanefi));

        relationRepo.save(new Relation(netNeutrality, cont2, hanefi));
        relationRepo.save(new Relation(airtelZero, cont2, hanefi));
        relationRepo.save(new Relation(freeBasics, cont2, hanefi));
        relationRepo.save(new Relation(india, cont2, hanefi));

        relationRepo.save(new Relation(india, cont3, hanefi));
        relationRepo.save(new Relation(freeBasics, cont3, hanefi));
        relationRepo.save(new Relation(markZuckerberg, cont3, hanefi));

        relationRepo.save(new Relation(india, cont4, hanefi));
        relationRepo.save(new Relation(freeBasics, cont4, hanefi));
        relationRepo.save(new Relation(markZuckerberg, cont4, hanefi));

        relationRepo.save(new Relation(india, cont5, hanefi));
        relationRepo.save(new Relation(freeBasics, cont5, hanefi));

        relationRepo.save(new Relation(internet, cont6, hanefi));

        relationRepo.save(new Relation(markZuckerberg, cont7, hanefi));
        relationRepo.save(new Relation(india, cont7, hanefi));

        relationRepo.save(new Relation(freeBasics, cont8, hanefi));
        relationRepo.save(new Relation(markZuckerberg, cont8, hanefi));
        relationRepo.save(new Relation(india, cont8, hanefi));

        relationRepo.save(new Relation(fcc, cont9, hanefi));
        relationRepo.save(new Relation(netNeutrality, cont9, hanefi));
        relationRepo.save(new Relation(internet, cont9, hanefi));

        relationRepo.save(new Relation(eef, cont10, hanefi));
        relationRepo.save(new Relation(internet, cont10, hanefi));
        relationRepo.save(new Relation(federalCourt, cont10, hanefi));

        relationRepo.save(new Relation(freedomOfPress, cont11, hanefi));
        relationRepo.save(new Relation(turkishCourt, cont11, hanefi));
        relationRepo.save(new Relation(syria, cont11, hanefi));

        relationRepo.save(new Relation(freedomOfPress, cont12, hanefi));
        relationRepo.save(new Relation(turkishCourt, cont12, hanefi));
        relationRepo.save(new Relation(censure, cont12, hanefi));

    }

    private int gcd(int i, int j) {
        return (j != 0) ? gcd(j, i % j) : i;
    }

    private void loadUsers() {
        hanefi = new User("hanefi", "hanefi", "hanefi@hanefi.com");
        abbas = new User("abbas", "abbas", "abbas@guclu.com");
        hasan = new User("hasan", "hasan", "hasan@dagi.com");


        log.info("trying to save user : " + hanefi.getUsername());

        userRepo.save(hanefi);
        userRepo.save(abbas);
        userRepo.save(hasan);

        log.info("Saved USER Hanefi - id : " + hanefi.getUsername());
    }

    private void loadComments() {
        commentRepo.save(new Comment("A supporting photo: http://specials-images.forbesimg.com/imageserve/503151122/960x0.jpg?fit=scale", abbas, cont5));

    }

    private void loadRates(){
        contributionRepo.findAll().forEach(contribution -> {
            if (contribution.getId()%4 == 0)
                rateRepo.save(new UserRate(contribution,hanefi,+1));
            else if (contribution.getId()%4 == 1)
                rateRepo.save(new UserRate(contribution,hanefi,-1));
        });
    }

}
