/**
 * 
 */
package com.pdpsoft.sample;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.NoWorkFoundException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;

/**
 * @author shayan
 *
 */
public class PlayerFlatFileItemReader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		FlatFileItemReader itemReader = new FlatFileItemReader();
		itemReader.setResource(new FileSystemResource("C:/Java/development/spring-batch-sample/src/main/resources/data.info"));
		itemReader.setFieldSetMapper(new PlayerMapper());
		itemReader.setLineTokenizer(new DelimitedLineTokenizer());
		//itemReader.setFirstLineIsHeader(true);
		itemReader.setLinesToSkip(1);
		itemReader.open(new ExecutionContext());
		
		List<PlayerEntity> players = new ArrayList<PlayerEntity>();
		try {
			PlayerEntity entity = (PlayerEntity) itemReader.read();
			if(entity == null) {
				System.out.println("Within if and entity is null !!!");
				return;
			}
			while(entity != null) {
				players.add(entity);
				entity = (PlayerEntity) itemReader.read();
			}
			System.out.println(players.size());
		} catch (UnexpectedInputException e) {
			e.printStackTrace();
		} catch (NoWorkFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
