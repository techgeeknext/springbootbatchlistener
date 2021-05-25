package com.techgeeknext.springbootbatchlistener.step;

import com.techgeeknext.springbootbatchlistener.model.AirportInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.Date;

public class AirportInfoProcessor
    implements ItemProcessor<AirportInfo, String> {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(AirportInfoProcessor.class);

  @Override
  public String process(AirportInfo airportInfo) throws Exception {
    System.out.println("===Airport Details===");
    String message = "Airport Code- "+airportInfo.getCode()+"  Airport Name- "
        + airportInfo.getAirport() + "  City-" + airportInfo.getCity()+"  Country- "+airportInfo.getCountry()
            +"  State- "+airportInfo.getState();
    LOGGER.info("==copied '{}' to output file", message);
    return message;
   }
}
