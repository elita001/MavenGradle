package mapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import mapping.interfaces.Mapable;
import model.domain.Car;
import model.domain.Manufacturer;
import model.domain.Order;
import model.domain.OrdersInfo;
import model.domain.Role;
import model.domain.Service;
import model.domain.User;
import model.domain.fields.Stage;
import model.entities.CarEntity;
import model.entities.ManufacturerEntity;
import model.entities.OrderEntity;
import model.entities.OrdersInfoEntity;
import model.entities.RoleEntity;
import model.entities.ServiceEntity;
import model.entities.UserEntity;
import model.entities.fields.Status;

public class Mapper implements Mapable {
	private MapperFacade mapperFacade;
	final static Logger logger = LoggerFactory.getLogger("app.Logger");

	public Mapper() {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
				.build();
		addClassMaps(mapperFactory);
		mapperFacade = mapperFactory.getMapperFacade();
	}

	/**
	 * @return Converted Object
	 * @param from
	 *            Object to convert
	 * @param destinationType
	 *            Type to convert
	 */
	public <F, T> T map(F from, Class<T> destinationType) {
		return mapperFacade.map(from, destinationType);
	}

	/**
	 * @param from
	 *            Object to convert
	 * @param to
	 *            instance for converted object
	 */
	public <F, T> void map(F from, T to) {
		mapperFacade.map(from, to);
	}

	/**
	 * Add classmaps to factory
	 * 
	 * @param factory
	 *            MapperFactory for adding classmaps
	 */
	public void addClassMaps(MapperFactory factory) {
		logger.debug("Start adding classmaps");
		factory.classMap(RoleEntity.class, Role.class).byDefault().register();
		factory.classMap(OrdersInfoEntity.class, OrdersInfo.class)
				.field("price", "oldPrice").byDefault().register();
		factory.classMap(ServiceEntity.class, Service.class)
				.field("curPrice", "currentPrice").byDefault().register();
		factory.classMap(UserEntity.class, User.class)
				.field("firstName", "name").field("lastName", "surname")
				.byDefault().register();
		factory.classMap(ManufacturerEntity.class, Manufacturer.class)
				.field("cars", "carsList").byDefault().register();
		factory.classMap(CarEntity.class, Car.class)
				.field("orders", "ordersList").field("customer", "client")
				.byDefault().register();
		factory.getConverterFactory().registerConverter(
				new CustomConverter<Status, Stage>() {
					public Stage convert(Status source,
							Type<? extends Stage> destinationType) {
						switch (source) {
						case processing:
							return Stage.accepted;
						case done:
							return Stage.executed;
						case open:
							return Stage.expected;
						default:
							return null;
						}
					}
				});
		factory.getConverterFactory().registerConverter(
				new CustomConverter<Stage, Status>() {
					public Status convert(Stage source,
							Type<? extends Status> destinationType) {
						switch (source) {
						case accepted:
							return Status.processing;
						case executed:
							return Status.done;
						case expected:
							return Status.open;
						default:
							return null;
						}
					}
				});
		factory.classMap(OrderEntity.class, Order.class)
				.field("status", "stage").byDefault().register();
		logger.debug("End adding classmaps");
	}
}
