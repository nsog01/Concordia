package domain.mappers.files;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.imageio.ImageIO;

import data.ClientTDG;
import data.LanguageTDG;
import domain.IList;
import domain.PersistentObject;
import domain.files.Client;
import domain.files.ClientList;
import domain.files.Language;
import domain.files.Relation;
import domain.mappers.Mapper;

public class ClientMapper implements Mapper {

	public Client read(int l) {
		Client c = null;
		try {
			ResultSet set = ClientTDG.read(l);
			set.next();
			c = map(set);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	public static ClientList readAllDependents(int houseId) {
		ClientList list = new ClientList();

		try {
			ResultSet set = ClientTDG.readAllDependents(houseId);
			while (set.next()) {
				Client client = map(set);
				list.add(client);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static Client readRepresentative(int houseId) {
		Client c = null;

		try {
			ResultSet set = ClientTDG.readRepresentative(houseId);
			set.next();
			c = map(set);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	static Client map(ResultSet set) {
		Client c = null;
		try {
			
			int ClientId = set.getInt(1);
			String firstName = set.getString(2);
			String middleName = set.getString(3);
			String lastName = set.getString(4);
			Date dob = new java.util.Date(set.getTimestamp(5).getTime());
			String sex= set.getString(6);
			String medicare = set.getString(9);
			Relation relation = new RelationMapper().read(set.getInt(11));
			String maritalStatus = set.getString(14);
			String workStatus = set.getString(15);
			String workStatusExtra = set.getString(16);
			String canadaStatus = set.getString(17);
			String canadaStatusExtra = set.getString(18);
			String origin = set.getString(19);
			String originExtra = set.getString(20);
			String referral = set.getString(21);
			String referralExtra = set.getString(22);
			String reason = set.getString(23);
			String reasonExtra = set.getString(24);
			Date registeredDate = new java.util.Date(set.getTimestamp(25).getTime());
			
			/* Languages */
			ResultSet querySet = LanguageTDG.readMother(ClientId);
			Language mother = null;
			Language second = null;
			if (querySet.next()) {
				mother = LanguageMapper.map(querySet);
			}
			querySet = LanguageTDG.readSecond(ClientId);
			if (querySet.next()) {
				second = LanguageMapper.map(querySet);
			}

			/* Images */
			BufferedImage image = null;
			BufferedImage thumb = null;
			InputStream in;
			byte[] imageBytes;
			imageBytes = set.getBytes(7);
			if (imageBytes != null) {
				in = new ByteArrayInputStream(imageBytes);
				image = ImageIO.read(in);
			}
			byte[] thumbBytes = set.getBytes(8);
			if (thumbBytes != null) {
				in = new ByteArrayInputStream(thumbBytes);
				thumb = ImageIO.read(in);
			}

			c = new Client(ClientId, firstName, middleName, lastName, image, thumb, dob, sex, medicare, mother, second,relation, maritalStatus, workStatus, workStatusExtra, canadaStatus, canadaStatusExtra, origin, originExtra, referral, referralExtra, reason, reasonExtra, registeredDate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	public void update(Client client) {
		try {
			ClientTDG.update(client.getId(), client.getFirstName(),
					client.getMiddleName(), client.getLastName(),
					client.getDOB(), client.getSex().toString(),
					client.getMedicare(), client.getMotherTongueId(),
					client.getSecondLanguageId(), client.getPhoto(),
					client.getThumb(),					
					client.getMaritalStatus().toString(),
					client.getWorkStatus().toString(),
					client.getExtraWorkStatusField(),
					client.getCanadianStatus().toString(),
					client.getExtraCanadianStatusField(),
					client.getOrigin().toString(),
					client.getExtraOriginField(),
					client.getReferral().toString(),
					client.getReferralExtra(),
					client.getReason().toString(),
					client.getReasonExtra(),
					client.getRegistrationDate()	);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateWithoutPhoto(Client client) {
		try {
			ClientTDG.updateWithoutPhoto(client.getId(), client.getFirstName(),
					client.getMiddleName(), client.getLastName(),
					client.getDOB(), client.getSex().toString(),
					client.getMedicare(), client.getMotherTongueId(),
					client.getSecondLanguageId(),
					client.getMaritalStatus().toString(),
					client.getWorkStatus().toString(),
					client.getExtraWorkStatusField(),
					client.getCanadianStatus().toString(),
					client.getExtraCanadianStatusField(),
					client.getOrigin().toString(),
					client.getExtraOriginField(),
					client.getReferral().toString(),
					client.getReferralExtra(),
					client.getReason().toString(),
					client.getReasonExtra(),
					client.getRegistrationDate());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Client insert(PersistentObject theClient) {
		Client client = (Client) theClient;
		
		try {
			client.setId(ClientTDG.insert(
					client.getFirstName(),
					client.getMiddleName(),
					client.getLastName(),
					client.getDOB(),
					client.getSex().toString(),
					client.getPhoto(),
					client.getThumb(),
					client.getMedicare(), 
					client.getMotherTongueId(), 
					client.getSecondLanguageId(),
					client.getMaritalStatus().toString(),
					client.getWorkStatus().toString(),
					client.getExtraWorkStatusField(),
					client.getCanadianStatus().toString(),
					client.getExtraCanadianStatusField(),
					client.getOrigin().toString(),
					client.getExtraOriginField(),
					client.getReferral().toString(),
					client.getReferralExtra(),
					client.getReason().toString(),
					client.getReasonExtra(),
					client.getRegistrationDate()					
					));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (Client) client;
	}

	@Override
	public void delete(PersistentObject client) {
		try {
			ClientTDG.delete(client.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public IList readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(PersistentObject object) {
		// TODO Auto-generated method stub
	}

	public static Image getPhoto(Client client) {
		BufferedImage image = null;
		try {
			ResultSet set = ClientTDG.getPhoto(client.getId());
			set.next();
			InputStream in;
			byte[] imageBytes;
			imageBytes = set.getBytes(1);
			if (imageBytes != null) {
				in = new ByteArrayInputStream(imageBytes);
				image = ImageIO.read(in);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
}
